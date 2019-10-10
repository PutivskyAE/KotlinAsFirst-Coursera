@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String = when {
    age % 100 in 11..14 -> "$age лет" // 11..14 лет
    age % 10 == 1 -> "$age год" // 1,21,31..ab год (ab - любое двухзначное число, заканчивающееся на b=1, кроме 11)
    age % 10 in 2..4 -> "$age года" // 2,22,32..cd года (cd - любое двухзначное число, заканчивающееся на d=2, кроме 12)
    else -> "$age лет" // все остальные случаи, не вошедшие в вышеупомянутыеy
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val halfWay = (t1 * v1 + t2 * v2 + t3 * v3) / 2 // вычисление половины пути
    return when {
    halfWay <= t1 * v1 -> halfWay / v1
    halfWay <= t1 * v1 + t2 * v2 -> t1 + (halfWay - t1 * v1) / v2
    else -> t1 + t2 + (halfWay - t1 * v1 - t2 * v2) / v3
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    return if ((kingX == rookX1 || kingX == rookX2) && (kingY == rookY1 || kingY ==rookY2)) 3 // угроза королю от обеих ладей
    else if (kingX == rookX1 || kingY == rookY1) 1 // угроза королю от первой ладьи
    else if (kingX == rookX2 || kingY == rookY2) 2 // угроза королю от второй ладьи
    else 0 // угрозы королю нет
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    return if ((kingX == rookX || kingY == rookY) && (((kingX - kingY) == (bishopX - bishopY)) || ((kingX + kingY) == (bishopX + bishopY)))) 3 // угроза королю от ладьи и слона
    else if (kingX == rookX || kingY == rookY) 1 // угроза королю от ладьи
    else if (((kingX - kingY) == (bishopX - bishopY)) || ((kingX + kingY) == (bishopX + bishopY))) 2 // угроза королю от слона
    else 0 // угрозы королю нет
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    return if ((a > b + c) || (b > a + c) || (c > b + a)) -1 // треугольника не существует
    else if ((a > b && a > c) && (a * a < b * b + c * c)) 0  // наибольшая сторона a, треугольник остроугольный
    else if (a * a > b * b + c * c) 2                        // наибольшая сторона a, треугольник тупоугольный
    else if (a * a == b * b + c * c) 1                       // наибольшая сторона a, треугольник прямоугольный
    else if ((b > a && b > c) && (b * b < a * a + c * c)) 0  // наибольшая сторона b, треугольник остроугольный
    else if (b * b > a * a + c * c) 2                        // наибольшая сторона b, треугольник тупоугольный
    else if (b * b == a * a + c * c) 1                       // наибольшая сторона b, треугольник прямоугольный
    else if ((c > a && b > a) && (c * c < a * a + b * b)) 0  // наибольшая сторона c, треугольник остроугольный
    else if (c * c > a * a + b * b) 2                        // наибольшая сторона c, треугольник тупоугольный
    else 1                                                   // наибольшая сторона c, треугольник прямоугольный
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    (a > d || b < c) -> -1                // пересечения нет
    (a <= d && d <= b && a <= c) -> d - c // пересечение есть
    (c <= b && b <= d && a < c) -> b - c  // пересечение есть
    (c <= a && b <= d) -> b - a           // пересечение есть
    else -> d - a                         // пересечение есть
}