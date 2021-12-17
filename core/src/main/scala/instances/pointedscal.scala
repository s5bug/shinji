package tf.bug.shinji
package instances

case class PointedFunction1[A, B](a: A, f: A => B)

object PointedScal extends Category[
  /* Obj = */ Any,
  /* Val = */ λ[α => α],
  /* Hom = */ PointedFunction1,
] {

  override def id[A <: Any](v: A): PointedFunction1[A, A] =
    PointedFunction1(v, Scal.id[A](()))

  override def compose[A <: Any, B <: Any, C <: Any](f: PointedFunction1[A, B], g: PointedFunction1[B, C]): PointedFunction1[A, C] =
    PointedFunction1(f.a, Scal.compose(f.f, g.f))

  override def homExtractLeft[A <: Any, B <: Any](hom: PointedFunction1[A, B]): A =
    hom.a

  override def homExtractRight[A <: Any, B <: Any](hom: PointedFunction1[A, B]): B =
    hom.f(hom.a)

  def liftMonad[F[_ <: Any] <: Any](from: Monad[Any, λ[α => Unit], Function1, F]): Monad[Any, λ[α => α], PointedFunction1, F] =
    new Monad[Any, λ[α => α], PointedFunction1, F] {
      override val endofunctorCategory: Category[Any, λ[α => α], PointedFunction1] = PointedScal

      override def mapVal[A <: Any](v: A): F[A] =
        from.pure(())(v)

      override def map[A <: Any, B <: Any](f: PointedFunction1[A, B]): PointedFunction1[F[A], F[B]] =
        PointedFunction1(mapVal(f.a), from.map(f.f))

      override def pure[A <: Any](v: A): PointedFunction1[A, F[A]] =
        PointedFunction1(v, from.pure(()))

      override def flatten[A <: Any](v: A): PointedFunction1[F[F[A]], F[A]] =
        PointedFunction1(mapVal(mapVal(v)), from.flatten(()))
    }

}

object OptionTAdjunction extends Adjunction[
  /* ObjE = */ Any,
  /* ValE = */ λ[α => Unit],
  /* HomE = */ Function1,
  /* ObjI = */ Any,
  /* ValI = */ λ[α => α],
  /* HomI = */ PointedFunction1,
  /* U = */ Option,
  /* F = */ λ[α => α],
] {

  override def free: Functor[Any, λ[α => Unit], Function1, Any, λ[α => α], PointedFunction1, Option] =
    new Functor[Any, λ[α => Unit], Function1, Any, λ[α => α], PointedFunction1, Option] {
      override val functorCategoryIn: Category[Any, λ[α => Unit], Function1] = Scal
      override val functorCategoryOut: Category[Any, λ[α => α], PointedFunction1] = PointedScal

      override def mapVal[A <: Any](v: Unit): Option[A] =
        None

      override def map[A <: Any, B <: Any](f: A => B): PointedFunction1[Option[A], Option[B]] =
        PointedFunction1(
          mapVal(functorCategoryIn.homExtractLeft(f)),
          _.map(f)
        )
    }

  override def forgetful: Functor[Any, λ[α => α], PointedFunction1, Any, λ[α => Unit], Function1, λ[α => α]] =
    new Functor[Any, λ[α => α], PointedFunction1, Any, λ[α => Unit], Function1, λ[α => α]] {
      override val functorCategoryIn: Category[Any, λ[α => α], PointedFunction1] = PointedScal
      override val functorCategoryOut: Category[Any, λ[α => Unit], Function] = Scal

      override def mapVal[A <: Any](v: A): Unit =
        ()

      override def map[A <: Any, B <: Any](f: PointedFunction1[A, B]): A => B =
        f.f
    }

  override def iso[A <: Any, B <: Any](va: Unit, vb: B): Iso[Any, λ[α => Unit], Function1, PointedFunction1[Option[A], B], A => B] =
    Iso[Any, λ[α => Unit], Function1, PointedFunction1[Option[A], B], A => B](
      forward = (f: PointedFunction1[Option[A], B]) => (x: A) => f.f(Some(x)),
      backward = (f: A => B) => PointedFunction1(None, (x: Option[A]) => x.map(f).getOrElse(vb)),
    )

}
