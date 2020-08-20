package utils

func Ternary(statement bool, a, b interface{}) interface{} {
	if statement {
		return a
	}
	return b
}

func IsBlank(str string) bool {
	if str == "" {
		return true
	}
	return false
}

type Ids struct {
	Ids uint `url:"ids,omitempty"`
}
