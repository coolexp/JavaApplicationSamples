package com.aicarb.service;

import com.aicarb.vo.ProductVO;
import com.aicarb.vo.User;

public interface UserService {
    public User getUserData(long userId);
    public ProductVO getProductData(int productId);
}
