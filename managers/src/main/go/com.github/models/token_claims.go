package models

import "github.com/dgrijalva/jwt-go"

type TokenClaims struct {
	jwt.StandardClaims
	FirstName string
	LastName  string
}
