package tf.bug.shinji

trait CartesianClosed[
  Obj,
  Con[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  Empty <: Obj,
  Prod[_ <: Obj, _ <: Obj] <: Obj,
  Exp[_ <: Obj, _ <: Obj] <: Obj
] extends Cartesian[Obj, Con, Hom, Empty, Prod] with ClosedMonoidal[Obj, Con, Hom, Empty, Prod, Exp] {
  def eval[A <: Obj, B <: Obj](implicit a: Con[A], b: Con[B]): Hom[Prod[Exp[A, B], A], B]
  def multiply[A <: Obj, B <: Obj](implicit a: Con[A], b: Con[B]): Hom[A, Exp[B, Prod[A, B]]]
}
