package tf.bug.shinji

/**
 * A Monoidal Category.
 *
 * @tparam Obj The upper bound type.
 * @tparam Hom The homset functor.
 * @tparam I The unit object.
 * @tparam Tens The tensor product functor.
 */
trait Monoidal[
  Obj,
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Tens[_ <: Obj, _ <: Obj] <: Obj
] extends Category[Obj, Hom] with Bifunctor[Obj, Hom, Obj, Hom, Obj, Hom, Tens] {
  /**
   * The forward map of the association isomorphism.
   */
  def associateRight[A <: Obj, B <: Obj, C <: Obj]: Hom[Tens[Tens[A, B], C], Tens[A, Tens[B, C]]]
  /**
   * The backwards map of the association isomorphism.
   */
  def associateLeft[A <: Obj, B <: Obj, C <: Obj]: Hom[Tens[A, Tens[B, C]], Tens[Tens[A, B], C]]

  override val leftBifunctorCategory: Category[Obj, Hom] = this
  override val rightBifunctorCategory: Category[Obj, Hom] = this
  override val outBifunctorCategory: Category[Obj, Hom] = this

  def unitorLeft[A <: Obj]: Hom[Tens[I, A], A]
  def deunitorLeft[A <: Obj]: Hom[A, Tens[I, A]]
  def unitorRight[A <: Obj]: Hom[Tens[A, I], A]
  def deunitorRight[A <: Obj]: Hom[A, Tens[A, I]]
}
