package models

type JwtToken struct {
	TokenType   string `json:"tokenType"`
	AccessToken string `json:"accessToken"`
}

func NewJwtToken(tokenType string, accessToken string) *JwtToken {
	return &JwtToken{TokenType: tokenType, AccessToken: accessToken}
}
