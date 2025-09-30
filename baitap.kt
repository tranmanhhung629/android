import kotlin.math.abs

class PhanSo(var tu: Int, var mau: Int) {

    // Hàm nhập phân số từ bàn phím
    fun nhap() {
        do {
            print("Nhập tử số: ")
            tu = readln().toInt()
            print("Nhập mẫu số: ")
            mau = readln().toInt()
        } while (tu == 0 || mau == 0) // yêu cầu nhập lại nếu tử hoặc mẫu = 0
    }

    // Hàm in phân số
    fun inPhanSo() {
        println("$tu/$mau")
    }

    // Hàm tối giản phân số
    fun toiGian() {
        val ucln = gcd(abs(tu), abs(mau))
        tu /= ucln
        mau /= ucln
        if (mau < 0) { // đổi dấu để mẫu dương
            tu = -tu
            mau = -mau
        }
    }

    // Hàm so sánh với 1 phân số khác
    fun soSanh(ps: PhanSo): Int {
        val a = tu * ps.mau
        val b = ps.tu * mau
        return when {
            a < b -> -1
            a == b -> 0
            else -> 1
        }
    }

    // Hàm cộng 2 phân số
    fun cong(ps: PhanSo): PhanSo {
        val tuMoi = tu * ps.mau + ps.tu * mau
        val mauMoi = mau * ps.mau
        val kq = PhanSo(tuMoi, mauMoi)
        kq.toiGian()
        return kq
    }

    // Hàm tìm ước chung lớn nhất (dùng đệ quy)
    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}

fun main() {
    print("Nhập số lượng phân số: ")
    val n = readln().toInt()
    val arr = Array(n) { PhanSo(1, 1) }

    // Nhập mảng phân số
    for (i in arr.indices) {
        println("Nhập phân số thứ ${i + 1}:")
        arr[i].nhap()
    }

    println("\nMảng phân số vừa nhập:")
    for (ps in arr) ps.inPhanSo()

    println("\nMảng sau khi tối giản:")
    for (ps in arr) {
        ps.toiGian()
        ps.inPhanSo()
    }

    // Tính tổng
    var tong = PhanSo(0, 1)
    for (ps in arr) {
        tong = tong.cong(ps)
    }
    println("\nTổng các phân số = ")
    tong.inPhanSo()

    // Tìm phân số lớn nhất
    var maxPS = arr[0]
    for (ps in arr) {
        if (ps.soSanh(maxPS) == 1) {
            maxPS = ps
        }
    }
    print("\nPhân số lớn nhất là: ")
    maxPS.inPhanSo()

    // Sắp xếp giảm dần (dùng sort với compare)
    println("\nMảng sau khi sắp xếp giảm dần:")
    val arrSorted = arr.sortedWith { a, b -> b.soSanh(a) }
    for (ps in arrSorted) ps.inPhanSo()
}
