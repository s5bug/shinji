package tf.bug.shinji

trait ClosedCategory[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Exp[_ <: Obj, _ <: Obj] <: Obj,
] extends Category[Obj, Val, Hom] {

  val closedExpProfunctor: Profunctor[Obj, Val, Hom, Obj, Val, Hom, Obj, Val, Hom, Exp]
  def expExtractLeft[A <: Obj, B <: Obj](v: Val[Exp[A, B]]): Val[A]
  def expExtractRight[A <: Obj, B <: Obj](v: Val[Exp[A, B]]): Val[B]

  def identity: I
  def identityVal: Val[I]

  def point[A <: Obj](v: Val[A]): Hom[A, Exp[I, A]]
  def jd[A <: Obj](v: Val[A]): Hom[I, Exp[A, A]]
  def read[X <: Obj, Y <: Obj, Z <: Obj](vx: Val[X], vy: Val[Y], vz: Val[Z]): Hom[Exp[Y, Z], Exp[Exp[X, Y], Exp[X, Z]]]

}
