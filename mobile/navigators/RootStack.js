import 'react-native-gesture-handler';
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import Login from './../screens/Auth/Login';
import SignUp from './../screens/Auth/signup';
import AppTabs from './AppTabs';
import Categories from './../screens/Debate/Categories';

const Stack = createNativeStackNavigator();

const RootStack = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator 
        screenOptions={{
          headerShown: false,
        }}
        initialRouteName='Login'
      >
        
        {/* Écrans d'authentification */}
        <Stack.Screen name="Login" component={Login} />
        <Stack.Screen name="SignUp" component={SignUp} />
        

        {/* Navigation principale avec onglets */}
        <Stack.Screen name="AppTabs" component={AppTabs} />
        <Stack.Screen name="Categories" component={Categories} />

      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default RootStack;