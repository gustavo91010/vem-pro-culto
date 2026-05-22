package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IgrejaServiceImpTest {

@InjectMocks
private IgrejaServiceImp igrejaServiceImp;


@Test
void test(){
assertEquals("ok", igrejaServiceImp.test());
// assertEquals("ook", igrejaServiceImp.test());
}
  



}
