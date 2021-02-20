package tf.bug.shinji

trait Category[
  Obj,
  Hom[_ <: Obj, _ <: Obj]
] extends Compose[Obj, Hom] {
  def id[A <: Obj]: Hom[A, A]
}
