import kotlinx.browser.window
import react.*
import react.dom.div
import react.dom.h1
import react.dom.p

external interface Film {
    var id: Long
    var title: String
    var type: String
}

val App = functionalComponent<RProps> {
    val (films, setFilms) = useState<List<Film>>(emptyList())

    useEffect(dependencies = listOf()) {
        window.fetch("/api/rentals/films")
            .then { response -> response.json() }
            .then { data -> setFilms(data.unsafeCast<List<Film>>()) }
    }

    div {
        h1 { +"Video Rental Store" }
        films.forEach { film ->
            p {
                +"${film.title} (${film.type})"
            }
        }
    }
}
