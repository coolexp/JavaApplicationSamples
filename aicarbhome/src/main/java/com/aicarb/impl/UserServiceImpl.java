package com.aicarb.impl;

import com.aicarb.service.UserService;
import com.aicarb.vo.ProductVO;
import com.aicarb.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public User getUserData(long userId) {
        return null;
    }
    @Override
    public ProductVO getProductData(int productId) {
        String sql="SELECT * from pfh_product_detail where id = ?";
        return (ProductVO)jdbcTemplate.queryForObject(sql,new Object[] {productId},new BeanPropertyRowMapper<ProductVO>(ProductVO.class));
    }
}
