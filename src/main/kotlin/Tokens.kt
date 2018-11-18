val tokens = hashMapOf(
    "KEYWORDS" to hashMapOf(
        "is" to "IS_KEYWORD"
    ),
    "DELIMITERS" to hashMapOf(
        " " to "SPACE_DELIMITER",
        "(" to "LEFT_ROUND_BRACKET",
        ")" to "RIGHT_ROUND_BRACKET",
        "[" to "LEFT_SQUARE_BRACKET",
        "]" to "RIGHT_SQUARE_BRACKET",
        "{" to "LEFT_CURLY_BRACKET",
        "}" to "RIGHT_CURLY_BRACKET"
    ),
    "OPERATORS" to hashMapOf(
        "+" to "PLUS_OPERATOR",
        "-" to "MINUS_OPERATOR",
        "*" to "MULTIPLY_OPERATOR",
        "/" to "DIVIDE_OPERATOR"
    )
)

fun main(args: Array<String>) {
    print(tokens)
}