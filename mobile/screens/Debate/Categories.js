import React from "react";
import { ScrollView, View } from "react-native";
import { Ionicons } from "@expo/vector-icons";

import {
  BackgroundContainer,
  InnerContainer,
  WhiteButton,
  ButtonText,
  Colors,
  Label,
  Choice,
  Shadow,
  WhiteContainer
} from "../../components/styles";

const { dark, yellow, blue, lightPink, pink, white, grey } = Colors;

const Categories = ({ navigation }) => {
  const categories = [
    { name: "Art", icon: "color-palette", bgColor: lightPink },
    { name: "Politique", icon: "megaphone", bgColor: blue },
    { name: "Culture", icon: "book", bgColor: yellow },
    { name: "Informatique", icon: "code", bgColor: pink },
    { name: "Tendance", icon: "trending-up", bgColor: lightPink },
    { name: "Industrie", icon: "construct", bgColor: blue },
    { name: "Philosophie", icon: "bulb", bgColor: yellow },
    { name: "Santé", icon: "medkit", bgColor: pink },
    { name: "Histoire", icon: "time", bgColor: lightPink },
    { name: "Musique", icon: "musical-notes", bgColor: blue },
  ];

  return (
    <BackgroundContainer
      source={require("../../assets/img/fond.png")}
      resizeMode="cover"
      style={{ flex: 1 }}
    >
        <Label style={{ 
          fontSize: 28, 
          marginBottom: 20, 
          color: dark,
          fontWeight: 'bold',
          textAlign: 'center'
        }}>
            Catégories
        </Label>
        
        <Label style={{ 
          fontSize: 16, 
          marginBottom: 30, 
          color: dark,
          textAlign: 'center'
        }}>
          Sélectionnez un domaine pour débuter votre débat
        </Label>

        <ScrollView 
          showsVerticalScrollIndicator={false}
          contentContainerStyle={{
            flexDirection: 'row',
            flexWrap: 'wrap',
            justifyContent: 'space-between',
            paddingBottom: 20,
          }}
        >
          {categories.map((category) => (
            <View key={category.name} style={{
              width: '48%',
              marginBottom: 20,
            }}>
              <Choice style={{
                backgroundColor: category.bgColor,
                opacity: 0.8,
                height: 150,
                width: '100%',
                justifyContent: 'center',
                alignItems: 'center',
              }}>
                <View style={{
                  alignItems: 'center',
                  justifyContent: 'center',
                }}>
                  <View style={{
                    backgroundColor: white,
                    width: 60,
                    height: 60,
                    borderRadius: 30,
                    alignItems: 'center',
                    justifyContent: 'center',
                    marginBottom: 15,
                  }}>
                    <Ionicons 
                      name={category.icon} 
                      size={30} 
                      color={dark} 
                    />
                  </View>
                  <ButtonText style={{
                    fontSize: 18,
                    color: dark,
                    fontWeight: 'bold',
                  }}>
                    {category.name}
                  </ButtonText>
                </View>
              </Choice>
            </View>
          ))}
        </ScrollView>

        {/* Bouton retour */}
        <View style={{ marginTop: 20, width: '100%' }}>
          <WhiteButton 
            onPress={() => navigation.goBack()}
            style={{ width: '100%' }}
          >
            <ButtonText>
              <Ionicons name="arrow-back" size={18} />
              {' '}Retour
            </ButtonText>
          </WhiteButton>
        </View>
    </BackgroundContainer>
  );
};

export default Categories;