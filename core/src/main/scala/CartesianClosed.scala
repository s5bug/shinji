package tf.bug.shinji

trait CartesianClosed[
  Obj,
  Hom[_ <: Obj, _ <: Obj],
  Empty <: Obj,
  Prod[_ <: Obj, _ <: Obj] <: Obj,
  Exp[_ <: Obj, _ <: Obj] <: Obj
] extends Cartesian[Obj, Hom, Empty, Prod] with ClosedMonoidal[Obj, Hom, Empty, Prod, Exp] {
  def eval[A <: Obj, B <: Obj]: Hom[Prod[Exp[A, B], A], B]
  def multiply[A <: Obj, B <: Obj]: Hom[A, Exp[B, Prod[A, B]]]
}
