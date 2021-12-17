package tf.bug.shinji

trait CartesianCategory[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Prod[_ <: Obj, _ <: Obj] <: Obj,
] extends SymmetricMonoidalCategory[Obj, Val, Hom, I, Prod] {

  def duplicate[X <: Obj](v: Val[X]): Hom[X, Prod[X, X]]
  def terminate[X <: Obj](v: Val[X]): Hom[X, I]

}
