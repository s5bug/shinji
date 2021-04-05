package tf.bug.shinji

/**
 * A Category without given identity morphisms.
 *
 * @tparam Obj The upper bound type.
 * @tparam Hom The homset functor.
 */
trait Compose[
  Obj,
  Hom[_ <: Obj, _ <: Obj]
] {
  /**
   * The composition operation for Hom.
   */
  def compose[A <: Obj, B <: Obj, C <: Obj](f: Hom[B, C], g: Hom[A, B]): Hom[A, C]
  def andThen[A <: Obj, B <: Obj, C <: Obj](f: Hom[A, B], g: Hom[B, C]): Hom[A, C] =
    compose(g, f)
}
