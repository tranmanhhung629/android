import kotlin.math.abs

// Class Fraction để biểu diễn phân số
class Fraction(var numerator: Int, var denominator: Int) {

    init {
        require(denominator != 0) { "Denominator cannot be zero" }
    }

    // Nhập phân số từ bàn phím
    fun input() {
        do {
            print("Enter numerator: ")
            numerator = readln().toInt()
            print("Enter denominator: ")
            denominator = readln().toInt()
        } while (denominator == 0) // Lặp lại nếu nhập mẫu = 0
    }

    // In phân số ra màn hình
    fun printFraction() {
        println("$numerator/$denominator")
    }

    // Rút gọn phân số về dạng tối giản
    fun simplify() {
        val gcdValue = gcd(abs(numerator), abs(denominator)) // tìm ước chung lớn nhất
        numerator /= gcdValue
        denominator /= gcdValue
        if (denominator < 0) { // đảm bảo mẫu số luôn dương
            numerator = -numerator
            denominator = -denominator
        }
    }

    // So sánh 2 phân số
    fun compare(other: Fraction): Int {
        val left = numerator * other.denominator
        val right = other.numerator * denominator
        return when {
            left < right -> -1
            left == right -> 0
            else -> 1
        }
    }
    // Cộng 2 phân số
    fun add(other: Fraction): Fraction {
        val newNumerator = numerator * other.denominator + other.numerator * denominator
        val newDenominator = denominator * other.denominator
        val result = Fraction(newNumerator, newDenominator)
        result.simplify() // luôn rút gọn sau khi cộng
        return result
    }
    // Hàm tính ước chung lớn nhất (GCD) bằng đệ quy
    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}
fun main() {
    print("Enter number of fractions: ")
    val n = readln().toInt()
    val fractions = Array(n) { Fraction(1, 1) }

    // Nhập mảng phân số
    for (i in fractions.indices) {
        println("Enter fraction ${i + 1}:")
        fractions[i].input()
    }

    println("\nFractions you entered:")
    fractions.forEach { it.printFraction() }

    println("\nFractions after simplification:")
    fractions.forEach {
        it.simplify()
        it.printFraction()
    }

    // Tính tổng các phân số
    var sum = Fraction(0, 1)
    for (f in fractions) {
        sum = sum.add(f)
    }
    println("\nSum of fractions = ")
    sum.printFraction()

    // Tìm phân số lớn nhất
    val maxFraction = fractions.maxWithOrNull { a, b -> a.compare(b) }!!
    print("\nLargest fraction is: ")
    maxFraction.printFraction()

    // Sắp xếp mảng giảm dần
    println("\nFractions sorted in descending order:")
    fractions.sortedWith { a, b -> b.compare(a) }.forEach { it.printFraction() }
}
