package utils

func Ternary(statement bool, a, b interface{}) interface{} {
	if statement {
		return a
	}
	return b
}

type Ids struct {
	Ids uint `url:"ids,omitempty"`
}
