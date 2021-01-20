package tf.bug.shinji
package instances

object Calc {

  // TODO make this into an example instead

  sealed trait CalcT
  case object CalcU extends CalcT
  sealed trait CalcN extends CalcT
  case object CalcZ extends CalcN
  case class CalcS(pred: CalcN) extends CalcN
  case class Calc2[A <: CalcT, B <: CalcT](l: A, r: B) extends CalcT

  sealed trait CalcA[A, B]
  case class Lift(nat: CalcN) extends CalcA[CalcU.type, CalcN]
  case object Inc extends CalcA[CalcN, CalcN]
  case class Duplicate[A <: CalcT]() extends CalcA[A, Calc2[A, A]]
  case class Terminate[A <: CalcT]() extends CalcA[A, CalcU.type]
  case class Bimap[A <: CalcT, B <: CalcT, C <: CalcT, D <: CalcT](f: CalcA[A, C], g: CalcA[B, D]) extends CalcA[Calc2[A, B], Calc2[C, D]]
  case class AndThen[A <: CalcT, B <: CalcT, C <: CalcT](f: CalcA[A, B], g: CalcA[B, C]) extends CalcA[A, C]
  case class Flip[A <: CalcT, B <: CalcT]() extends CalcA[Calc2[A, B], Calc2[B, A]]
  case class First[A <: CalcT, B <: CalcT, C <: CalcT](f: CalcA[A, B]) extends CalcA[Calc2[A, C], Calc2[B, C]]
  case class AssocR[A <: CalcT, B <: CalcT, C <: CalcT]() extends CalcA[Calc2[Calc2[A, B], C], Calc2[A, Calc2[B, C]]]
  case class AssocL[A <: CalcT, B <: CalcT, C <: CalcT]() extends CalcA[Calc2[A, Calc2[B, C]], Calc2[Calc2[A, B], C]]
  case class Identity[A <: CalcT]() extends CalcA[A, A]
  case class Unitor[A <: CalcT]() extends CalcA[Calc2[CalcU.type, A], A]
  case class Deunitor[A <: CalcT]() extends CalcA[A, Calc2[CalcU.type, A]]

  implicit object CalcArrowEv extends Cartesian[CalcT, CalcA, CalcU.type, Calc2] with Arrow[CalcT, CalcA, CalcU.type, Calc2, CalcA] {

    override def duplicate[A <: CalcT]: CalcA[A, Calc2[A, A]] =
      Duplicate()

    override def terminate[A <: CalcT]: CalcA[A, CalcU.type] =
      Terminate()

    override def lift[A <: CalcT, B <: CalcT](f: CalcA[A, B]): CalcA[A, B] =
      f

    override def bimap[A <: CalcT, B <: CalcT, C <: CalcT, D <: CalcT](f: CalcA[A, C], g: CalcA[B, D]): CalcA[Calc2[A, B], Calc2[C, D]] =
      Bimap(f, g)

    override val strongProfunctorCategory: Monoidal[CalcT, CalcA, CalcU.type, Calc2] = this

    override def first[A <: CalcT, B <: CalcT, C <: CalcT]: CalcA[A, B] => CalcA[Calc2[A, C], Calc2[B, C]] =
      First(_)

    override def second[A <: CalcT, B <: CalcT, C <: CalcT]: CalcA[A, B] => CalcA[Calc2[C, A], Calc2[C, B]] =
      f => AndThen(Flip(), AndThen(First[A, B, C](f), Flip()))

    override def associateRight[A <: CalcT, B <: CalcT, C <: CalcT]: CalcA[Calc2[Calc2[A, B], C], Calc2[A, Calc2[B, C]]] =
      AssocR()

    override def associateLeft[A <: CalcT, B <: CalcT, C <: CalcT]: CalcA[Calc2[A, Calc2[B, C]], Calc2[Calc2[A, B], C]] =
      AssocL()

    override def unitorLeft[A <: CalcT]: CalcA[Calc2[CalcU.type, A], A] =
      Unitor()

    override def deunitorLeft[A <: CalcT]: CalcA[A, Calc2[CalcU.type, A]] =
      Deunitor()

    override def unitorRight[A <: CalcT]: CalcA[Calc2[A, CalcU.type], A] =
      AndThen(Flip(), Unitor())

    override def deunitorRight[A <: CalcT]: CalcA[A, Calc2[A, CalcU.type]] =
      AndThen(Deunitor(), Flip())

    override def id[A <: CalcT]: CalcA[A, A] = Identity()

    override def compose[A <: CalcT, B <: CalcT, C <: CalcT](f: CalcA[B, C], g: CalcA[A, B]): CalcA[A, C] =
      AndThen(g, f)
  }

}
