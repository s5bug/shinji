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
  Con[_ <: Obj],
  Hom[_ <: Obj, _ <: Obj],
  I <: Obj,
  Tens[_ <: Obj, _ <: Obj] <: Obj
] extends Category[Obj, Con, Hom] with Bifunctor[Obj, Con, Hom, Obj, Con, Hom, Obj, Con, Hom, Tens] {

  implicit def unitConstraint: Con[I]

  /**
   * The forward map of the association isomorphism.
   */
  def associateRight[A <: Obj, B <: Obj, C <: Obj](implicit a: Con[A], b: Con[B], c: Con[C]): Hom[Tens[Tens[A, B], C], Tens[A, Tens[B, C]]]
  /**
   * The backwards map of the association isomorphism.
   */
  def associateLeft[A <: Obj, B <: Obj, C <: Obj](implicit a: Con[A], b: Con[B], c: Con[C]): Hom[Tens[A, Tens[B, C]], Tens[Tens[A, B], C]]

  override final def leftBifunctorCategory: Category[Obj, Con, Hom] = this
  override final def rightBifunctorCategory: Category[Obj, Con, Hom] = this
  override final def outBifunctorCategory: Category[Obj, Con, Hom] = this

  def unitorLeft[A <: Obj](implicit a: Con[A]): Hom[Tens[I, A], A]
  def deunitorLeft[A <: Obj](implicit a: Con[A]): Hom[A, Tens[I, A]]
  def unitorRight[A <: Obj](implicit a: Con[A]): Hom[Tens[A, I], A]
  def deunitorRight[A <: Obj](implicit a: Con[A]): Hom[A, Tens[A, I]]

}
