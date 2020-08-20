package config

import (
	"github.com/hudl/fargo"
)

func ConnectEureka(config *Config) *fargo.EurekaConnection {
	con := fargo.NewConn(config.EurekaConfig.EurekaUrl)
	return &con
}
