package tf.bug.shinji

trait Category[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
] extends Compose[Obj, Val, Hom] {

  def id[A <: Obj](v: Val[A]): Hom[A, A]

}
