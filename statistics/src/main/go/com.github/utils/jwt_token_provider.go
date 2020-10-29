package utils

import (
	"../models"
	"encoding/json"
	"github.com/dgrijalva/jwt-go"
)

func GetSubject(data string) (string, error) {
	var jwtToken models.JwtToken
	err := json.Unmarshal([]byte(data), &jwtToken)
	if err != nil {
		return "", err
	}
	claims := &jwt.StandardClaims{}
	_, err = jwt.ParseWithClaims(jwtToken.AccessToken, claims, key)
	return claims.Subject, err
}

func key(token *jwt.Token) (interface{}, error) {
	return []byte(""), nil
}
