package utils

import (
	"encoding/json"
	"github.com/dgrijalva/jwt-go"
	"statistics/src/main/go/com.github/models"
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
