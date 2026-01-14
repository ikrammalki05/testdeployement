import React, { useState, useEffect } from 'react';
import { 
  ScrollView, 
  RefreshControl, 
  View, 
  Text, 
  TouchableOpacity, 
  Image,
  Platform
} from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Ionicons } from '@expo/vector-icons';

// Importez vos styles existants
import { 
  BackgroundContainer, 
  InnerContainer, 
  StyledButton, 
  ButtonText, 
  WhiteButton,
  Label,
  TextLink,
  TextLinkContent,
  Colors,
  Shadow
} from '../../components/styles';

const { dark, yellow, blue, lightPink, pink, white, grey, brand, green, darkLight } = Colors;

const Dashboard = ({ navigation }) => {
  const [user, setUser] = useState({
    nom: "Utilisateur",
    prenom: "Test",
    email: "test@email.com",
    score: 0,
    badge: "Nouveau"
  });
  
  const [refreshing, setRefreshing] = useState(false);

  useEffect(() => {
    loadUserData();
  }, []);

  const loadUserData = async () => {
    try {
      const nom = await AsyncStorage.getItem('nom') || "Utilisateur";
      const prenom = await AsyncStorage.getItem('prenom') || "Test";
      const email = await AsyncStorage.getItem('email') || "test@email.com";
      const score = parseInt(await AsyncStorage.getItem('score')) || 0;
      const badgeMom = await AsyncStorage.getItem('badgeMom') || "Nouveau Débatteur";
      const badgeCategorie = await AsyncStorage.getItem('badgeCategorie') || "BRONZE";
      
      setUser({ 
        nom, 
        prenom, 
        email, 
        score, 
        badge: badgeMom,
        category: badgeCategorie
      });
    } catch (error) {
      console.log("Erreur:", error);
    }
  };

  const onRefresh = async () => {
    setRefreshing(true);
    await loadUserData();
    setRefreshing(false);
  };

  return (
    <BackgroundContainer 
      source={require("../../assets/img/fond.png")} 
      resizeMode="cover"
    >
      <ScrollView 
        showsVerticalScrollIndicator={false}
        refreshControl={
          <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
        }
      >
        <InnerContainer style={{ paddingBottom: 30 }}>
          
          {/* Header avec titre */}
          <View style={{ alignItems: 'center', marginBottom: 20, marginTop: 10 }}>
            <Label style={{ fontSize: 24, fontWeight: 'bold', color: white, marginBottom: 5 }}>
              Tableau de Bord
            </Label>
            <Label style={{ fontSize: 14, color: white }}>
              Bienvenue {user.prenom} !
            </Label>
          </View>

          {/* Carte Profil */}
          <Shadow style={{ 
            backgroundColor: white,
            borderRadius: 38,
            padding: 20,
            marginBottom: 20,
            width: '100%'
          }}>
            <View style={{ flexDirection: 'row', alignItems: 'center', marginBottom: 15 }}>
              <Image 
                source={{ uri: 'https://via.placeholder.com/80' }}
                style={{ 
                  width: 80, 
                  height: 80, 
                  borderRadius: 40, 
                  marginRight: 15,
                  borderWidth: 2,
                  borderColor: yellow
                }}
              />
              <View style={{ flex: 1 }}>
                <Text style={{ fontSize: 20, fontWeight: 'bold', color: dark }}>
                  {user.prenom} {user.nom}
                </Text>
                <Text style={{ fontSize: 14, color: grey, marginTop: 5 }}>
                  {user.email}
                </Text>
                <View style={{ flexDirection: 'row', alignItems: 'center', marginTop: 10 }}>
                  <Ionicons name="trophy" size={16} color={yellow} />
                  <Text style={{ marginLeft: 5, fontSize: 14, color: dark }}>
                    {user.score} points
                  </Text>
                </View>
              </View>
            </View>
            
            {/* Badge */}
            <View style={{ 
              backgroundColor: brand,
              paddingVertical: 8,
              paddingHorizontal: 20,
              borderRadius: 20,
              alignSelf: 'flex-start',
              marginBottom: 15
            }}>
              <Text style={{ color: white, fontWeight: 'bold', fontSize: 12 }}>
                {user.badge}
              </Text>
            </View>
            
            {/* Bouton modifier profil */}
            <StyledButton 
              style={{ 
                backgroundColor: brand,
                marginBottom: 0,
                marginTop: 10
              }}
              onPress={() => navigation.navigate('Profil')}
            >
              <ButtonText style={{ color: white }}>
                <Ionicons name="create-outline" size={16} />
                {' '}Modifier le profil
              </ButtonText>
            </StyledButton>
          </Shadow>

          {/* Section Niveau et Progression */}
          <Shadow style={{ 
            backgroundColor: white,
            borderRadius: 38,
            padding: 20,
            marginBottom: 20,
            width: '100%'
          }}>
            <Text style={{ fontSize: 18, fontWeight: 'bold', color: dark, marginBottom: 15, textAlign: 'center' }}>
              Niveau {Math.floor(user.score / 100) + 1}
            </Text>
            
            {/* Barre de progression */}
            <View style={{ 
              height: 12, 
              backgroundColor: lightPink, 
              borderRadius: 6, 
              overflow: 'hidden', 
              marginBottom: 10 
            }}>
              <View style={{ 
                height: '100%', 
                backgroundColor: green, 
                borderRadius: 6, 
                width: `${(user.score % 100)}%` 
              }} />
            </View>
            
            <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
              <Text style={{ color: grey, fontSize: 12 }}>
                {user.score % 100}/100 points
              </Text>
              <View style={{ 
                flexDirection: 'row', 
                alignItems: 'center', 
                backgroundColor: `${yellow}30`, 
                paddingVertical: 5, 
                paddingHorizontal: 10, 
                borderRadius: 15 
              }}>
                <Ionicons name="star" size={14} color={yellow} />
                <Text style={{ marginLeft: 5, fontSize: 12 }}>Niveau {Math.floor(user.score / 100) + 1}</Text>
              </View>
            </View>
          </Shadow>

          {/* Section Statistiques */}
          <View style={{ width: '100%', marginBottom: 20 }}>
            <Text style={{ 
              fontSize: 18, 
              fontWeight: 'bold', 
              color: white, 
              marginBottom: 15,
              textAlign: 'center'
            }}>
              Mes Statistiques
            </Text>
            
            <View style={{ flexDirection: 'row', flexWrap: 'wrap', justifyContent: 'space-between' }}>
              <StatCard 
                icon="chatbubbles" 
                value="12" 
                label="Débats" 
                color={blue}
              />
              <StatCard 
                icon="trophy" 
                value="8" 
                label="Victoires" 
                color={green}
              />
              <StatCard 
                icon="trending-up" 
                value="67%" 
                label="Taux de win" 
                color={yellow}
              />
              <StatCard 
                icon="flame" 
                value="3" 
                label="Suite" 
                color={pink}
              />
            </View>
          </View>

          {/* Section Badges */}
          <Shadow style={{ 
            backgroundColor: white,
            borderRadius: 38,
            padding: 20,
            marginBottom: 20,
            width: '100%'
          }}>
            <Text style={{ fontSize: 18, fontWeight: 'bold', color: dark, marginBottom: 15, textAlign: 'center' }}>
              Mes Badges
            </Text>
            
            <ScrollView horizontal showsHorizontalScrollIndicator={false} style={{ paddingVertical: 10 }}>
              <BadgeItem icon="rocket" name="Débutant" color={pink} unlocked={true} />
              <BadgeItem icon="megaphone" name="Orateur" color={blue} unlocked={true} />
              <BadgeItem icon="chatbubbles" name="Persuasif" color={yellow} unlocked={user.score > 100} />
              <BadgeItem icon="school" name="Expert" color={green} unlocked={user.score > 300} />
            </ScrollView>
          </Shadow>

          {/* Section Derniers Débats */}
          <Shadow style={{ 
            backgroundColor: white,
            borderRadius: 38,
            padding: 20,
            width: '100%'
          }}>
            <Text style={{ fontSize: 18, fontWeight: 'bold', color: dark, marginBottom: 15, textAlign: 'center' }}>
              Derniers Débats
            </Text>
            
            <DebateItem 
              title="Le télétravail est-il l'avenir ?" 
              date="15/11/2023" 
              result="Gagné" 
              points="+50" 
            />
            <DebateItem 
              title="L'IA va-t-elle remplacer les emplois ?" 
              date="10/11/2023" 
              result="Perdu" 
              points="-20" 
            />
            
            <View style={{ alignItems: 'center', marginTop: 15 }}>
              <TextLink onPress={() => navigation.navigate('DebateHistory')}>
                <TextLinkContent style={{ color: brand, fontSize: 14, fontWeight: '600' }}>
                  Voir tout l'historique →
                </TextLinkContent>
              </TextLink>
            </View>
          </Shadow>

          {/* Actions Rapides */}
          <View style={{ width: '100%', marginTop: 20, marginBottom: 30 }}>
            <Text style={{ 
              fontSize: 18, 
              fontWeight: 'bold', 
              color: white, 
              marginBottom: 15,
              textAlign: 'center'
            }}>
              Actions Rapides
            </Text>
            
            <WhiteButton 
              style={{ marginBottom: 10, alignSelf: 'center' }}
              onPress={() => navigation.navigate('Débat')}
            >
              <ButtonText style={{ color: dark }}>
                <Ionicons name="search" size={16} />
                {' '}Nouveau Débat
              </ButtonText>
            </WhiteButton>
            
            <WhiteButton 
              style={{ marginBottom: 10, alignSelf: 'center' }}
              onPress={() => navigation.navigate('DebateHistory')}
            >
              <ButtonText style={{ color: dark }}>
                <Ionicons name="time" size={16} />
                {' '}Voir l'Historique
              </ButtonText>
            </WhiteButton>
            
            <WhiteButton 
            style= {{alignSelf: 'center'}}
              onPress={() => navigation.navigate('Settings')}
            >
              <ButtonText style={{ color: dark }}>
                <Ionicons name="settings" size={16} />
                {' '}Paramètres
              </ButtonText>
            </WhiteButton>
          </View>

        </InnerContainer>
      </ScrollView>
    </BackgroundContainer>
  );
};

// Composant de carte de statistique
const StatCard = ({ icon, value, label, color }) => (
  <View style={{ 
    width: '48%', 
    backgroundColor: white, 
    borderRadius: 20, 
    padding: 15, 
    marginBottom: 15,
    alignItems: 'center',
    ...Platform.select({
      ios: {
        shadowColor: '#000',
        shadowOpacity: 0.1,
        shadowRadius: 4,
        shadowOffset: { width: 0, height: 2 },
      },
      android: {
        elevation: 4,
      },
    })
  }}>
    <View style={{ 
      width: 40, 
      height: 40, 
      borderRadius: 20, 
      backgroundColor: `${color}20`, 
      alignItems: 'center', 
      justifyContent: 'center', 
      marginBottom: 10 
    }}>
      <Ionicons name={icon} size={20} color={color} />
    </View>
    <Text style={{ fontSize: 24, fontWeight: 'bold', color: dark, marginBottom: 5 }}>{value}</Text>
    <Text style={{ fontSize: 12, color: grey, textAlign: 'center' }}>{label}</Text>
  </View>
);

// Composant de badge
const BadgeItem = ({ icon, name, color, unlocked }) => (
  <View style={{ alignItems: 'center', marginRight: 20, width: 80 }}>
    <View style={{ 
      width: 60, 
      height: 60, 
      borderRadius: 30, 
      alignItems: 'center', 
      justifyContent: 'center',
      backgroundColor: unlocked ? color : '#F5F5F5',
      marginBottom: 8,
      borderWidth: 2,
      borderColor: unlocked ? color : 'transparent'
    }}>
      <Ionicons 
        name={unlocked ? icon : 'lock-closed'} 
        size={24} 
        color={unlocked ? white : grey} 
      />
    </View>
    <Text style={{ 
      fontSize: 12, 
      fontWeight: '600', 
      color: unlocked ? dark : grey, 
      textAlign: 'center' 
    }}>
      {name}
    </Text>
  </View>
);

// Composant d'élément de débat
const DebateItem = ({ title, date, result, points }) => {
  const getResultColor = (result) => {
    switch (result) {
      case 'Gagné': return green;
      case 'Perdu': return pink;
      case 'Nul': return yellow;
      default: return grey;
    }
  };

  return (
    <View style={{ 
      borderBottomWidth: 1, 
      borderBottomColor: lightPink, 
      paddingVertical: 15 
    }}>
      <Text style={{ 
        fontSize: 16, 
        fontWeight: '600', 
        color: dark, 
        marginBottom: 8,
        textAlign: 'center'
      }}>
        {title}
      </Text>
      <View style={{ 
        flexDirection: 'row', 
        justifyContent: 'space-between', 
        alignItems: 'center', 
        marginBottom: 5 
      }}>
        <View style={{ flexDirection: 'row', alignItems: 'center' }}>
          <Ionicons name="calendar" size={14} color={grey} />
          <Text style={{ marginLeft: 5, fontSize: 12, color: grey }}>{date}</Text>
        </View>
        <Text style={{ 
          fontWeight: 'bold', 
          fontSize: 14, 
          color: getResultColor(result) 
        }}>
          {result}
        </Text>
      </View>
      <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
        <Text style={{ color: grey, fontSize: 12 }}>Société</Text>
        <Text style={{ 
          fontWeight: 'bold',
          color: points.startsWith('+') ? green : pink,
          fontSize: 14
        }}>
          {points} points
        </Text>
      </View>
    </View>
  );
};

export default Dashboard;