package com.yeqifu.growth.service.impl;

import com.yeqifu.growth.entity.Certificate;
import com.yeqifu.growth.mapper.CertificateMapper;
import com.yeqifu.growth.service.ICertificateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxc-
 * @since 2022-02-28
 */
@Service
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements ICertificateService {

    @Autowired
    private CertificateMapper certificateMapper;
    @Override
    public Integer countCertificate() {
        return certificateMapper.countCertificate();
    }
}
