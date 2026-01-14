import { 
  View, 
  TouchableOpacity, 
  Image, 
  Alert, 
  ScrollView, 
  ActivityIndicator,
  TextInput,
  Modal,
  Text 
} from "react-native";
import React, {useState, useEffect} from "react";
import * as ImagePicker from "expo-image-picker";
import { Ionicons } from "@expo/vector-icons";
import AsyncStorage from "@react-native-async-storage/async-storage";

// Importez uniquement les styles qui existent
import {
  BackgroundContainer,
  InnerContainer,
  Label,
  Colors,
  StyledButton,
  ButtonText,
  Shadow,
  WhiteButton,
  TextLink,
  TextLinkContent
} from "../../components/styles"

const {blue, dark, yellow, grey, white, brand} = Colors;

const Profil = ({ navigation }) => {
  const [image, setImage] = useState(null);
  const [isUpdating, setIsUpdating] = useState(false);
  
  // Données utilisateur simplifiées
  const [userData, setUserData] = useState({
    nom: "Dupont",
    prenom: "Jean",
    email: "jean.dupont@email.com",
    score: 450,
    badge: "Orateur",
    level: 2
  });

  // États pour la modification
  const [editModalVisible, setEditModalVisible] = useState(false);
  const [editField, setEditField] = useState("");
  const [editValue, setEditValue] = useState("");

  // Charger les données depuis AsyncStorage
  useEffect(() => {
    loadUserData();
  }, []);

  const loadUserData = async () => {
    try {
      const nom = await AsyncStorage.getItem('nom') || "Utilisateur";
      const prenom = await AsyncStorage.getItem('prenom') || "Test";
      const email = await AsyncStorage.getItem('email') || "test@email.com";
      const score = parseInt(await AsyncStorage.getItem('score')) || 0;
      const badge = await AsyncStorage.getItem('badgeMom') || "Nouveau Débatteur";
      
      setUserData({
        nom,
        prenom,
        email,
        score,
        badge,
        level: Math.floor(score / 100) + 1
      });
    } catch (error) {
      console.log("Erreur:", error);
    }
  };

  const pickImage = async () => {
    const permissionResult = await ImagePicker.requestMediaLibraryPermissionsAsync();
    
    if (!permissionResult.granted) {
      Alert.alert("Permission requise", "Autorisez l'accès à la galerie");
      return;
    }

    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [1, 1],
      quality: 0.7,
    });

    if (!result.canceled) {
      setImage(result.assets[0].uri);
      Alert.alert("Succès", "Photo changée (simulation)");
    }
  };

  const openEditModal = (field, value) => {
    setEditField(field);
    setEditValue(value);
    setEditModalVisible(true);
  };

  const updateUserInfo = () => {
    if (!editValue.trim()) {
      Alert.alert("Erreur", "Ce champ ne peut pas être vide");
      return;
    }

    setIsUpdating(true);
    
    // Simulation de mise à jour
    setTimeout(() => {
      setUserData(prev => ({
        ...prev,
        [editField]: editValue
      }));
      
      // Mettre à jour AsyncStorage
      AsyncStorage.setItem(editField, editValue);
      
      Alert.alert("Succès", "Informations mises à jour (simulation)");
      setEditModalVisible(false);
      setIsUpdating(false);
    }, 1000);
  };

  const handleLogout = async () => {
    Alert.alert(
      "Déconnexion",
      "Voulez-vous vraiment vous déconnecter ?",
      [
        { text: "Annuler", style: "cancel" },
        { 
          text: "Déconnexion", 
          style: "destructive",
          onPress: async () => {
            try {
              await AsyncStorage.clear();
              navigation.reset({
                index: 0,
                routes: [{ name: "Login" }],
              });
            } catch (error) {
              console.log("Erreur déconnexion:", error);
            }
          }
        }
      ]
    );
  };

  return ( 
    <BackgroundContainer
      source={require("../../assets/img/fond.png")}
      resizeMode="cover"
    >
      <ScrollView showsVerticalScrollIndicator={false}>
        <InnerContainer style={{ paddingBottom: 40 }}>

          {/* Photo de profil */}
          <View style={{ alignItems: "center", marginBottom: 30, marginTop: 20 }}>
            <View style={{ position: "relative" }}>
              <Image
                source={
                  image
                    ? { uri: image }
                    : require("../../assets/icons/homme.png")
                }
                style={{
                  width: 120,
                  height: 120,
                  borderRadius: 60,
                  borderWidth: 3,
                  borderColor: yellow
                }}
              />
              
              <TouchableOpacity
                onPress={pickImage}
                style={{
                  position: "absolute",
                  bottom: 0,
                  right: 0,
                  backgroundColor: brand,
                  borderRadius: 20,
                  padding: 8,
                }}
              >
                <Ionicons name="camera" size={20} color={white} />
              </TouchableOpacity>
            </View>
            
            <Label style={{ fontSize: 24, marginTop: 15, color: white, fontWeight: 'bold' }}>
              {userData.prenom} {userData.nom}
            </Label>
            
            <View style={{ 
              backgroundColor: yellow,
              paddingVertical: 6,
              paddingHorizontal: 20,
              borderRadius: 20,
              marginTop: 10
            }}>
              <Text style={{ color: dark, fontWeight: 'bold', fontSize: 14 }}>
                {userData.badge}
              </Text>
            </View>
          </View>

          {/* Section principale avec fond blanc*/}
          <View style={{ 
            backgroundColor: white,
            borderRadius: 38,
            padding: 25,
            width: '100%',
            minHeight: 500,
            marginTop: 20,
            shadowColor: '#000',
            shadowOffset: { width: 0, height: 4 },
            shadowOpacity: 0.1,
            shadowRadius: 8,
            elevation: 8
          }}>
            
            {/* Informations personnelles */}
            <View style={{ marginBottom: 30, width: '100%' }}>
              <Label style={{ fontSize: 20, marginBottom: 20, color: dark, fontWeight: '600', textAlign: 'center' }}>
                Mes Informations
              </Label>
              
              {/* Nom */}
              <View style={{ marginBottom: 20 }}>
                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                  <Label style={{ fontSize: 14, color: grey, marginBottom: 5 }}>Nom</Label>
                  <TouchableOpacity onPress={() => openEditModal('nom', userData.nom)}>
                    <Ionicons name="create-outline" size={18} color={brand} />
                  </TouchableOpacity>
                </View>
                <View style={{ 
                  backgroundColor: '#F8F9FA', 
                  padding: 15, 
                  borderRadius: 10,
                  borderWidth: 1,
                  borderColor: '#E0E0E0'
                }}>
                  <Text style={{ fontSize: 16, color: dark }}>{userData.nom}</Text>
                </View>
              </View>
              
              {/* Prénom */}
              <View style={{ marginBottom: 20 }}>
                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                  <Label style={{ fontSize: 14, color: grey, marginBottom: 5 }}>Prénom</Label>
                  <TouchableOpacity onPress={() => openEditModal('prenom', userData.prenom)}>
                    <Ionicons name="create-outline" size={18} color={brand} />
                  </TouchableOpacity>
                </View>
                <View style={{ 
                  backgroundColor: '#F8F9FA', 
                  padding: 15, 
                  borderRadius: 10,
                  borderWidth: 1,
                  borderColor: '#E0E0E0'
                }}>
                  <Text style={{ fontSize: 16, color: dark }}>{userData.prenom}</Text>
                </View>
              </View>
              
              {/* Email */}
              <View style={{ marginBottom: 30 }}>
                <View style={{ flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' }}>
                  <Label style={{ fontSize: 14, color: grey, marginBottom: 5 }}>Email</Label>
                  <TouchableOpacity onPress={() => openEditModal('email', userData.email)}>
                    <Ionicons name="create-outline" size={18} color={brand} />
                  </TouchableOpacity>
                </View>
                <View style={{ 
                  backgroundColor: '#F8F9FA', 
                  padding: 15, 
                  borderRadius: 10,
                  borderWidth: 1,
                  borderColor: '#E0E0E0'
                }}>
                  <Text style={{ fontSize: 16, color: dark }}>{userData.email}</Text>
                </View>
              </View>
            </View>

            {/* Statistiques */}
            <View style={{ 
              backgroundColor: '#F0F7FF',
              borderRadius: 15,
              padding: 20,
              marginBottom: 30,
              width: '100%'
            }}>
              <Label style={{ fontSize: 20, marginBottom: 15, color: dark, fontWeight: '600', textAlign: 'center' }}>
                📊 Mes Statistiques
              </Label>
              
              <View style={{ flexDirection: 'row', justifyContent: 'space-around', alignItems: 'center' }}>
                <View style={{ alignItems: 'center' }}>
                  <View style={{ 
                    width: 70, 
                    height: 70, 
                    borderRadius: 35, 
                    backgroundColor: brand,
                    justifyContent: 'center',
                    alignItems: 'center',
                    marginBottom: 10
                  }}>
                    <Text style={{ fontSize: 24, fontWeight: 'bold', color: white }}>
                      {userData.level}
                    </Text>
                  </View>
                  <Text style={{ fontSize: 14, color: dark, fontWeight: '600' }}>Niveau</Text>
                </View>
                
                <View style={{ alignItems: 'center' }}>
                  <View style={{ 
                    width: 70, 
                    height: 70, 
                    borderRadius: 35, 
                    backgroundColor: yellow,
                    justifyContent: 'center',
                    alignItems: 'center',
                    marginBottom: 10
                  }}>
                    <Text style={{ fontSize: 24, fontWeight: 'bold', color: dark }}>
                      {userData.score}
                    </Text>
                  </View>
                  <Text style={{ fontSize: 14, color: dark, fontWeight: '600' }}>Points</Text>
                </View>
              </View>
              
              {/* Barre de progression */}
              <View style={{ marginTop: 20 }}>
                <View style={{ 
                  flexDirection: 'row', 
                  justifyContent: 'space-between',
                  marginBottom: 5
                }}>
                  <Text style={{ fontSize: 12, color: grey }}>Progression</Text>
                  <Text style={{ fontSize: 12, color: brand, fontWeight: '600' }}>
                    {userData.score % 100}/100
                  </Text>
                </View>
                <View style={{ 
                  height: 8, 
                  backgroundColor: '#E0E0E0', 
                  borderRadius: 4,
                  overflow: 'hidden'
                }}>
                  <View style={{ 
                    height: '100%', 
                    backgroundColor: brand, 
                    width: `${userData.score % 100}%`
                  }} />
                </View>
              </View>
            </View>

            {/* Boutons d'action */}
            <View style={{ width: '100%', gap: 15, marginBottom: 20 }}>
              <WhiteButton style={{alignSelf: 'center'}} onPress={() => navigation.navigate('Accueil')}>
                <ButtonText>
                  <Ionicons name="home" size={16} />
                  {' '}Retour au Dashboard
                </ButtonText>
              </WhiteButton>
              
              <WhiteButton style={{alignSelf: 'center'}} onPress={() => navigation.navigate('Settings')}>
                <ButtonText>
                  <Ionicons name="settings" size={16} />
                  {' '}Paramètres
                </ButtonText>
              </WhiteButton>
            </View>

            {/* Déconnexion */}
            <Shadow style={{ width: '100%', marginTop: 10 }}>
              <StyledButton 
                onPress={handleLogout}
                style={{ backgroundColor: '#FF6B6B' }}
              >
                <ButtonText>
                  <Ionicons name="log-out" size={16} />
                  {' '}Déconnexion
                </ButtonText>
              </StyledButton>
            </Shadow>

            {/* Lien vers les conditions */}
            <View style={{ marginTop: 30, alignItems: 'center' }}>
              <TextLink onPress={() => navigation.navigate('Terms')}>
                <TextLinkContent style={{ color: grey, fontSize: 12 }}>
                  Conditions d'utilisation • Politique de confidentialité
                </TextLinkContent>
              </TextLink>
            </View>

          </View>

        </InnerContainer>
      </ScrollView>

      {/* Modal pour modifier les informations */}
      <Modal
        animationType="slide"
        transparent={true}
        visible={editModalVisible}
        onRequestClose={() => setEditModalVisible(false)}
      >
        <View style={{ 
          flex: 1, 
          justifyContent: 'center', 
          alignItems: 'center',
          backgroundColor: 'rgba(0, 0, 0, 0.5)'
        }}>
          <View style={{
            backgroundColor: white,
            borderRadius: 15,
            padding: 25,
            width: '85%',
            alignItems: 'center'
          }}>
            <Label style={{ fontSize: 20, marginBottom: 20, color: dark, fontWeight: '600' }}>
              Modifier {editField === 'nom' ? 'le nom' : editField === 'prenom' ? 'le prénom' : 'l\'email'}
            </Label>
            
            <TextInput
              style={{
                width: '100%',
                height: 50,
                borderWidth: 1,
                borderColor: '#ddd',
                borderRadius: 10,
                paddingHorizontal: 15,
                fontSize: 16,
                marginBottom: 25,
                backgroundColor: '#F8F9FA'
              }}
              value={editValue}
              onChangeText={setEditValue}
              placeholder={`Nouveau ${editField}`}
              autoCapitalize={editField === 'email' ? 'none' : 'words'}
              keyboardType={editField === 'email' ? 'email-address' : 'default'}
            />
            
            <View style={{ flexDirection: 'row', width: '100%', gap: 15 }}>
              <TouchableOpacity
                style={{
                  flex: 1,
                  backgroundColor: '#f0f0f0',
                  padding: 15,
                  borderRadius: 10,
                  alignItems: 'center'
                }}
                onPress={() => setEditModalVisible(false)}
              >
                <Text style={{ color: '#666', fontWeight: '600' }}>Annuler</Text>
              </TouchableOpacity>
              
              <TouchableOpacity
                style={{
                  flex: 1,
                  backgroundColor: brand,
                  padding: 15,
                  borderRadius: 10,
                  alignItems: 'center'
                }}
                onPress={updateUserInfo}
                disabled={isUpdating}
              >
                {isUpdating ? (
                  <ActivityIndicator size="small" color={white} />
                ) : (
                  <Text style={{ color: white, fontWeight: '600' }}>Enregistrer</Text>
                )}
              </TouchableOpacity>
            </View>
          </View>
        </View>
      </Modal>

    </BackgroundContainer>
  );
};

export default Profil;