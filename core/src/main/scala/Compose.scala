package tf.bug.shinji

trait Compose[Obj, Hom[_ <: Obj, _ <: Obj]] {
  def compose[A <: Obj, B <: Obj, C <: Obj](f: Hom[B, C], g: Hom[A, B]): Hom[A, C]
  def andThen[A <: Obj, B <: Obj, C <: Obj](f: Hom[A, B], g: Hom[B, C]): Hom[A, C]
}
