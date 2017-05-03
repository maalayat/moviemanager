package ec.solmedia.moviemanager.view.feature

import ec.solmedia.moviemanager.model.Movie
import rx.Observable

class MovieManager {

    fun getNowPlayingMovies(): Observable<List<Movie>> {
        return Observable.create { subscriber ->
            subscriber.onNext(initDummyDataNowPlaying())
        }
    }

    fun getUpComingMovies(): Observable<List<Movie>> {
        return Observable.create { subscriber ->
            subscriber.onNext(initDummyDataUpComing())
        }
    }

    private fun initDummyDataUpComing(): List<Movie> {
        val moviesUp = mutableListOf<Movie>()

        moviesUp.add(Movie("277834", "Rings", "Julia (Matilda Lutz) becomes worried about her boyfriend, Holt (Alex Roe) when he explores the dark urban legend of a mysterious videotape said to kill the watcher seven days after viewing. She sacrifices herself to save her boyfriend and in doing so makes a horrifying discovery: there is a \\\"movie within the movie\\\" that no one has ever seen before.", 0f, 0, "https://image.tmdb.org/t/p/w342/uwNNLJULvTQfgc3PBZAY92EOJQO.jpg", "https://image.tmdb.org/t/p/w780/91WPDonXsxRzi7AcfedKM3p3NFU.jpg"))
        moviesUp.add(Movie("121856", "The Great Wall", "The story of an elite force making a last stand for humanity on the world’s most iconic structure.", 0f, 0, "https://image.tmdb.org/t/p/w342/hm0Z5tpRlSzPO97U5e2Q32Y0Xrb.jpg", "https://image.tmdb.org/t/p/w780/yESCAoZkaxZ2AMiHojl9jYYd9zD.jpg"))
        moviesUp.add(Movie("330459", "Patriots Day", "An account of Boston Police Commissioner Ed Davis's actions in the events leading up to the 2013 Boston Marathon bombing and the aftermath, which includes the city-wide manhunt to find the terrorists behind it.", 0f, 0, "https://image.tmdb.org/t/p/w342/gd4SDPZIdVRAxUolQ9ZCNXTkQUq.jpg", "https://image.tmdb.org/t/p/w780/tiBL4PeaCPKGBz3qO4dJP2KzKop.jpg"))
        moviesUp.add(Movie("283366", "T2 Trainspotting", "First there was an opportunity......then there was a betrayal. Twenty years have gone by. Much has changed but just as much remains the same. ", 0f, 0, "https://image.tmdb.org/t/p/w342/A84pxL5z86KbV9u8GAK2mVOoXK.jpg", "https://image.tmdb.org/t/p/w780/dxJynNxoG6jRttYPoXOGx7OWJvq.jpg"))

        return moviesUp
    }

    private fun initDummyDataNowPlaying(): List<Movie> {
        val movies = mutableListOf<Movie>()

        movies.add(Movie("277834", "Moana", "In Ancient Polynesia, when a terrible curse incurred by Maui reaches an impetuous Chieftain's daughter's island, she answers the Ocean's call to seek out the demigod to set things right.", 6.5f, 854, "https://image.tmdb.org/t/p/w342/z4x0Bp48ar3Mda8KiPD1vwSY3D8.jpg", "https://image.tmdb.org/t/p/w780/1qGzqGUd1pa05aqYXGSbLkiBlLB.jpg"))
        movies.add(Movie("121856", "Passengers", "A spacecraft traveling to a distant colony planet and transporting thousands of people has a malfunction in its sleep chambers. As a result, two passengers are awakened 90 years early.", 6.2f, 745, "https://image.tmdb.org/t/p/w342/5gJkVIVU7FDp7AfRAbPSvvdbre2.jpg", "https://image.tmdb.org/t/p/w780/5EW4TR3fWEqpKsWysNcBMtz9Sgp.jpg"))
        movies.add(Movie("330459", "Assassin's Creed", "Lynch discovers he is a descendant of the secret Assassins society through unlocked genetic memories that allow him to relive the adventures of his ancestor, Aguilar, in 15th Century Spain. After gaining incredible knowledge and skills he’s poised to take on the oppressive Knights Templar in the present day.", 5.3f, 691, "https://image.tmdb.org/t/p/w342/tIKFBxBZhSXpIITiiB5Ws8VGXjt.jpg", "https://image.tmdb.org/t/p/w780/5EW4TR3fWEqpKsWysNcBMtz9Sgp.jpg"))
        movies.add(Movie("283366", "Rogue One: A Star Wars Story", "A rogue band of resistance fighters unite for a mission to steal the Death Star plans and bring a new hope to the galaxy.", 7.2f, 1802, "https://image.tmdb.org/t/p/w342/qjiskwlV1qQzRCjpV0cL9pEMF9a.jpg", "https://image.tmdb.org/t/p/w780/tZjVVIYXACV4IIIhXeIM59ytqwS.jpg"))
        movies.add(Movie("313369", "La La Land", "Mia, an aspiring actress, serves lattes to movie stars in between auditions and Sebastian, a jazz musician, scrapes by playing cocktail party gigs in dingy bars, but as success mounts they are faced with decisions that begin to fray the fragile fabric of their love affair, and the dreams they worked so hard to maintain in each other threaten to rip them apart.", 8f, 396, "https://image.tmdb.org/t/p/w342/ylXCdC106IKiarftHkcacasaAcb.jpg", "https://image.tmdb.org/t/p/w780/nadTlnTE6DdgmYsN4iWc2a2wiaI.jpg"))

        return movies
    }
}