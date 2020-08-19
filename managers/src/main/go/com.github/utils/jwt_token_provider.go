package utils

import (
	"../models"
	"encoding/json"
	"github.com/dgrijalva/jwt-go"
	"strconv"
)

func GetToken(data string) (*models.TokenClaims, error) {
	var jwtToken models.JwtToken
	err := json.Unmarshal([]byte(data), &jwtToken)
	if err != nil {
		return nil, err
	}
	claims := &models.TokenClaims{}
	_, err = jwt.ParseWithClaims(jwtToken.AccessToken, claims, key)
	return claims, err
}

func GetSubject(data string) (uint, error) {
	var jwtToken models.JwtToken
	err := json.Unmarshal([]byte(data), &jwtToken)
	if err != nil {
		return 0, err
	}
	claims := &jwt.StandardClaims{}
	_, err = jwt.ParseWithClaims(jwtToken.AccessToken, claims, key)
	result, err := strconv.ParseUint(claims.Subject, 10, 64)
	return uint(result), err
}

func key(token *jwt.Token) (interface{}, error) {
	return []byte(""), nil
}
