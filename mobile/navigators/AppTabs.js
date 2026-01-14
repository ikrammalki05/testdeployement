import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { Ionicons } from '@expo/vector-icons';

// Importez vos écrans
import Dashboard from '../screens/UserInformation/Dashboard';
import Profil from '../screens/UserInformation/Profil';
import NewDebate from '../screens/Debate/NewDebate';

const Tab = createBottomTabNavigator();

const AppTabs = () => {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        tabBarIcon: ({ focused, color, size }) => {
          let iconName;

          if (route.name === 'Accueil') {
            iconName = focused ? 'home' : 'home-outline';
          } else if (route.name === 'Débat') {
            iconName = focused ? 'chatbubbles' : 'chatbubbles-outline';
          } else if (route.name === 'Profil') {
            iconName = focused ? 'person' : 'person-outline';
          }

          return <Ionicons name={iconName} size={size} color={color} />;
        },
       
        tabBarShowLabel: true,
        tabBarActiveTintColor: '#fff',
        tabBarInactiveTintColor: '#fff', // Couleur avec transparence
        tabBarLabelStyle: {
          fontSize: 12,
          fontWeight: '500',
          marginBottom: 2,
        },
        
        
        tabBarStyle: {
          backgroundColor: 'rgba(48, 26, 75, 0.6)', // Blanc à 70% opaque
          borderTopWidth: 0.5,
          borderTopColor: 'rgba(48, 26, 75, 0.1)', // Bordure très discrète
          height: 60,
          paddingBottom: 5,
          paddingTop: 5,
          // Position absolue pour qu'elle flotte
          position: 'absolute',
          // Pour Android
          elevation: 0, // Réduit l'élévation pour moins d'ombre
          // Pour iOS
          shadowColor: '#000',
          shadowOffset: { width: 0, height: -1 },
          shadowOpacity: 0.05, // Ombre très légère
          shadowRadius: 2,
        },
        // Pas d'en-tête sur les écrans
        headerShown: false,
      })}
    >
      <Tab.Screen 
        name="Accueil" 
        component={Dashboard}
        options={{
          tabBarLabel: 'Accueil',
        }}
      />
      <Tab.Screen 
        name="Débat" 
        component={NewDebate}
        options={{
          tabBarLabel: 'Débat',
        }}
      />
      <Tab.Screen 
        name="Profil" 
        component={Profil}
        options={{
          tabBarLabel: 'Profil',
        }}
      />
    </Tab.Navigator>
  );
};

export default AppTabs;