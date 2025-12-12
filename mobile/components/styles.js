import styled from 'styled-components/native';
import { View, Text, Image, ImageBackground, TextInput, TouchableOpacity } from 'react-native';
import Constants from 'expo-constants';

const StatusBarHeight = Constants.statusBarHeight;

// Colors
export const Colors = {
  dark: "#301A4B",
  yellow: "#FFC482",
  blue: "#6DB1BF",
  lightPink: "#C8ADC0",
  pink: "#DB7F8E",
  white: "#FFFFFF",
  grey: "#999999"
};

const { dark, yellow, blue, lightPink, pink, white } = Colors;

export const StyledContainer = styled.View`
  flex: 1;
  padding: 25px;
  background-color: ${dark};
  padding-top: ${StatusBarHeight + 10}px;
`;

export const InnerContainer = styled.View`
  flex: 1;
  width: 100%;
  align-items: center;
`;

export const PageLogo = styled.Image`
  width: 300;
  height: 140px;
  shadow-color: #000;
  shadow-offset: {
    width: 10;
    height: 10;
  };
  shadow-opacity: 0.75 ;
  shadow-radius: 0.75px;
  elevation: 6;
`;
 
export const TextLink = styled.TouchableOpacity`
 justify-content: center;
 align-items: center;
`;

export const TextLinkContent = styled.Text`
 font-size: 12px;
 color: ${white};
`;


export const BackgroundContainer = styled(ImageBackground)`
  flex: 1;
  padding: 25px;
  padding-top: ${StatusBarHeight + 10}px;
`;

export const StyledFormArea = styled.View`
  width: 90%;
  padding-top: 55px;
`;

export const StyledTextInput = styled.TextInput`
  background-color: ${white};
  text-align: center;
  padding-left: 55px;
  padding-right: 55px;
  border-radius: 38px;
  font-size: 16px;
  height: 60px;
  margin-vertical: 3px;
  margin-bottom: 10px;
  shadow-color: #000;
  shadow-offset: {
    width: 0;
    height: 5;
  };
  shadow-opacity: 0.75 ;
  shadow-radius: 4.65px;
  elevation: 6;
`;

export const LeftIcon = styled.View`
   left: 15px;
   top: 38px;
   position: absolute;
   z-index: 1;
`;

export const RightIcon = styled.TouchableOpacity`
   right: 10px;
   top: 15px;
   position: absolute;
   z-index: 1;
`;

export const StyledButton = styled.TouchableOpacity`
  background-color: ${yellow};
  justify-content: center;
  padding-left: 55px;
  padding-right: 55px;
  border-radius: 38px;
  height: 60px;
  align-items: center;
  margin-vertical: 3px;
  margin-bottom: 10px;
  shadow-color: #000;
  shadow-offset: {
    width: 0;
    height: 5;
  };
  shadow-opacity: 0.75 ;
  shadow-radius: 4.65px;
  elevation: 6;
`;

export const ButtonText = styled.Text`
 color: ${dark};
 font-size: 20px;
 font-weight: bold;
`;


 


