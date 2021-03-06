package com.healthmanager.service;

import com.healthmanager.dao.VerificationCodeMapper;
import com.healthmanager.exception.BusinessException;
import com.healthmanager.model.VerificationCode;
import com.healthmanager.model.VerificationCodeExample;
import com.healthmanager.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class VerificationCodeService {

    @Resource
    private VerificationCodeMapper verificationCodeMapper;

    public void sendCode(String email){
        int randNum = 100000 + (int)(Math.random() * ((999999 - 100000)));
        EmailUtil.sendMailAsync(email,"验证码","您的验证码为"+randNum+",有效期15分钟");
        VerificationCodeExample verificationCodeExample=new VerificationCodeExample();
        VerificationCodeExample.Criteria criteria = verificationCodeExample.createCriteria();
        criteria.andEmailEqualTo(email);
        long result = verificationCodeMapper.countByExample(verificationCodeExample);
        if(result==0){
            VerificationCode verificationCode=new VerificationCode();
            verificationCode.setEmail(email);
            verificationCode.setCode(randNum+"");
            verificationCode.setTime(new Date());
            verificationCodeMapper.insert(verificationCode);
        }
        else{
            VerificationCode verificationCode=new VerificationCode();
            verificationCode.setEmail(email);
            verificationCode.setCode(randNum+"");
            verificationCode.setTime(new Date());
            verificationCodeMapper.updateByExampleSelective(verificationCode,verificationCodeExample);
        }
    }

    public void checkCode(String email,String code) {
        VerificationCodeExample verificationCodeExample=new VerificationCodeExample();
        VerificationCodeExample.Criteria criteria = verificationCodeExample.createCriteria();
        criteria.andEmailEqualTo(email)
                .andCodeEqualTo(code)
                .andTimeGreaterThan(new Date(new Date().getTime()-15*60*1000));
        long result = verificationCodeMapper.countByExample(verificationCodeExample);
        if(result==0){
            throw new BusinessException("验证码错误或已失效");
        }
        VerificationCode verificationCode=new VerificationCode();
        verificationCode.setTime(new Date(new Date().getTime()-24*60*60*1000));
        verificationCodeMapper.updateByExampleSelective(verificationCode,verificationCodeExample);
    }
}
