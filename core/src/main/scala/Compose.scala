package tf.bug.shinji

trait Compose[
  Obj,
  Val[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
] {

  def homExtractLeft[A <: Obj, B <: Obj](hom: Hom[A, B]): Val[A]
  def homExtractRight[A <: Obj, B <: Obj](hom: Hom[A, B]): Val[B]

  def compose[A <: Obj, B <: Obj, C <: Obj](f: Hom[A, B], g: Hom[B, C]): Hom[A, C]

}
