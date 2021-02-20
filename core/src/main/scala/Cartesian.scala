package tf.bug.shinji

trait Cartesian[
  Obj,
  Hom[_ <: Obj, _ <: Obj],
  Empty <: Obj,
  Prod[_ <: Obj, _ <: Obj] <: Obj
] extends Symmetric[Obj, Hom, Empty, Prod] {
  def duplicate[A <: Obj]: Hom[A, Prod[A, A]]
  def terminate[A <: Obj]: Hom[A, Empty]
}
