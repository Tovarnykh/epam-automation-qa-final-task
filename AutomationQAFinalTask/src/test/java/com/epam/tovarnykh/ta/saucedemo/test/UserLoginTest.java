package com.epam.tovarnykh.ta.saucedemo.test;

import com.epam.tovarnykh.ta.saucedemo.model.User;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UserLoginTest extends CommonConditions{

    @ParameterizedTest
    @MethodSource("provideUserConfigurations")
    @Tag("uc-1")
    public void createNewPasteTest(User user) {
    }


}
