package racing.v2.domain

@JvmInline
value class CarName(val name: String) {
    init {
        require(name.length <= 5) { "자동차 이름은 5자를 초과할 수 없습니다." }
    }
}
