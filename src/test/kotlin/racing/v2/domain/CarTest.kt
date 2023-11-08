package racing.v2.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class `자동차 프러퍼티 테스트` : StringSpec({
    "자동차 위치의 초기 값은 0이다." {
        val car = Car()
        car.position shouldBe 0
    }

    "자동차 이름의 초기 값은 \"이름없음\"이다." {
        val car = Car()
        car.name shouldBe "이름없음"
    }

    "자동차의 이름이 5글자가 초과하면 예외가 발생한다." {
        listOf("5글자보다큼", "이건일곱글자임", "여덟글자라서예외").forEach {
            shouldThrow<IllegalArgumentException> {
                Car(it)
            }.message shouldBe "자동차 이름은 5자를 초과할 수 없습니다."
        }
    }
})

class `자동차_목록_반환_테스트` : StringSpec({
    "자동차 3대가 쉼표(,)로 구분된 문자열로 들어오면 3개의 자동차 목록을 반환한다." {
        val 자동차_목록 = Car.ofList("alex,jason,pobi")
        자동차_목록 shouldHaveSize 3
    }
})

class `자동차 전진 테스트` : BehaviorSpec({

    Given("자동차 객체가 주어질 때") {
        val 알렉스_자동차 = Car()

        When("임의의 수가 4 이상일 경우") {
            listOf(4, 5, 6, 7, 8, 9).forEach { number ->
                Then("자동차 위치는 1 증가한다.") {
                    val originPosition = 알렉스_자동차.position
                    알렉스_자동차.moveForward(CarDrivingPolicy(FixedNumberGenerator(number)))
                    알렉스_자동차.position shouldBe originPosition + 1
                }
            }
        }

        When("임의의 수가 3 이하일 경우") {
            listOf(1, 2, 3).forEach { number ->
                Then("자동차 위치는 기존과 동일하다") {
                    val originPosition = 알렉스_자동차.position
                    알렉스_자동차.moveForward(CarDrivingPolicy(FixedNumberGenerator(number)))
                    알렉스_자동차.position shouldBe originPosition
                }
            }
        }
    }
})
