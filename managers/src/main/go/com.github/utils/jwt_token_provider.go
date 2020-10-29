package utils

import (
	"../models"
	"encoding/json"
	"github.com/dgrijalva/jwt-go"
)

func GetToken(data string) *models.TokenClaims {
	var jwtToken models.JwtToken
	err := json.Unmarshal([]byte(data), &jwtToken)
	if err != nil {
		return nil
	}
	claims := &models.TokenClaims{}
	_, err = jwt.ParseWithClaims(jwtToken.AccessToken, claims, key)
	return claims
}

func GetSubject(data string) (string, error) {
	var jwtToken models.JwtToken
	err := json.Unmarshal([]byte(data), &jwtToken)
	if err != nil {
		return "", err
	}
	claims := &jwt.StandardClaims{}
	_, err = jwt.ParseWithClaims(jwtToken.AccessToken, claims, key)
	if err != nil {
		return "", err
	}
	return claims.Subject, nil
}

func key(token *jwt.Token) (interface{}, error) {
	return []byte(""), nil
}
