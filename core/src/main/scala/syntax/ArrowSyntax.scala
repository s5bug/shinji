package tf.bug.shinji
package syntax

trait ArrowSyntax {

  implicit class ArrowOps[Obj, Hom[_ <: Obj, _ <: Obj], Empty <: Obj, Prod[_ <: Obj, _ <: Obj] <: Obj, F[_ <: Obj, _ <: Obj] <: Obj, A <: Obj, B <: Obj](f: F[A, B])(implicit arr: Arrow[Obj, Hom, Empty, Prod, F]) {

    def andThen[C <: Obj](g: F[B, C]): F[A, C] = arr.andThen(f, g)
    def compose[C <: Obj](g: F[C, A]): F[C, B] = arr.compose(f, g)
    def dimap[C <: Obj, D <: Obj](g: Hom[C, A], h: Hom[B, D]): F[C, D] = arr.dimap(g, h)(f)
    def first[C <: Obj]: F[Prod[A, C], Prod[B, C]] = arr.first[A, B, C](f)
    def ldimap[C <: Obj](g: Hom[C, A]): F[C, B] = arr.ldimap(g)(f)
    def merge[C <: Obj](g: F[A, C]): F[A, Prod[B, C]] = arr.merge(f, g)
    def rdimap[C <: Obj](g: Hom[B, C]): F[A, C] = arr.rdimap(g)(f)
    def second[C <: Obj]: F[Prod[C, A], Prod[C, B]] = arr.second[A, B, C](f)
    def split[C <: Obj, D <: Obj](g: F[C, D]): F[Prod[A, C], Prod[B, D]] = arr.split(f, g)

    def &&&[C <: Obj](g: F[A, C]): F[A, Prod[B, C]] = arr.merge(f, g)
    def ***[C <: Obj, D <: Obj](g: F[C, D]): F[Prod[A, C], Prod[B, D]] = arr.split(f, g)
    def <<<[C <: Obj](g: F[C, A]): F[C, B] = arr.compose(f, g)
    def >>>[C <: Obj](g: F[B, C]): F[A, C] = arr.andThen(f, g)

  }

  implicit class ArrowLiftSyntax[Obj, Hom[_ <: Obj, _ <: Obj], Empty <: Obj, Prod[_ <: Obj, _ <: Obj] <: Obj, A <: Obj, B <: Obj](hom: Hom[A, B])(implicit cart: Cartesian[Obj, Hom, Empty, Prod]) {

    def lift[F[_ <: Obj, _ <: Obj]](implicit arr: Arrow[Obj, Hom, Empty, Prod, F]): F[A, B] = arr.lift(hom)

  }

  def unitL[Obj, Hom[_ <: Obj, _ <: Obj], Empty <: Obj, Prod[_ <: Obj, _ <: Obj] <: Obj, A <: Obj, F[_, _]](implicit arr: Arrow[Obj, Hom, Empty, Prod, F]): F[Prod[Empty, A], A] =
    arr.unitorLeft[A]

  def unitR[Obj, Hom[_ <: Obj, _ <: Obj], Empty <: Obj, Prod[_ <: Obj, _ <: Obj] <: Obj, A <: Obj, F[_, _]](implicit arr: Arrow[Obj, Hom, Empty, Prod, F]): F[Prod[A, Empty], A] =
    arr.unitorRight[A]

  def deunitL[Obj, Hom[_ <: Obj, _ <: Obj], Empty <: Obj, Prod[_ <: Obj, _ <: Obj] <: Obj, A <: Obj, F[_, _]](implicit arr: Arrow[Obj, Hom, Empty, Prod, F]): F[A, Prod[Empty, A]] =
    arr.deunitorLeft[A]

  def deunitR[Obj, Hom[_ <: Obj, _ <: Obj], Empty <: Obj, Prod[_ <: Obj, _ <: Obj] <: Obj, A <: Obj, F[_, _]](implicit arr: Arrow[Obj, Hom, Empty, Prod, F]): F[A, Prod[A, Empty]] =
    arr.deunitorRight[A]

}
