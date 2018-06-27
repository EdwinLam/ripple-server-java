package com.ripple.service;

import com.ripple.dao.MallTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Edwin
 */
@Service("MallTypeServiceImpl")
public class MallTypeServiceImpl implements IMallTypeService {
    @Autowired
    private MallTypeDAO mallTypeDao;
}