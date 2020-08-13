package utils

import (
	"../models"
	"encoding/json"
	"fmt"
	"github.com/dgrijalva/jwt-go"
	"strconv"
)

func GetSubject(data string) (uint, error) {
	var jwtToken models.JwtToken
	err := json.Unmarshal([]byte(data), &jwtToken)
	if err != nil {
		return 0, err
	}
	claims := &jwt.StandardClaims{}
	_, err = jwt.ParseWithClaims(jwtToken.AccessToken, claims, key)
	fmt.Println(claims.Subject)
	result, err := strconv.ParseUint(claims.Subject, 10, 64)
	return uint(result), err
}

func key(token *jwt.Token) (interface{}, error) {
	//todo change hardcode
	return []byte("empty"), nil
}
