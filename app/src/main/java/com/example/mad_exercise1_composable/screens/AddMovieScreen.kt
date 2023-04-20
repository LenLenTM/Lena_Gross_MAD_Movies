package com.example.mad_exercise1_composable.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mad_exercise1_composable.R
import com.example.mad_exercise1_composable.models.Genre
import com.example.mad_exercise1_composable.models.ListItemSelectable
import com.example.mad_exercise1_composable.models.Movie
import com.example.mad_exercise1_composable.utils.InjectorUtils
import com.example.mad_exercise1_composable.widgets.SimpleAppBar
import com.example.movieappmad23.viewModels.AddMovieViewModel
import com.example.movieappmad23.viewModels.FavoritesViewModel
import com.example.movieappmad23.viewModels.MoviesViewModel
import kotlinx.coroutines.launch

@Composable
fun AddMovieScreen(navController: NavController){

    val viewModel: AddMovieViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current))

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SimpleAppBar(arrowBackClicked = {navController.popBackStack()}){
                Text(text = "Add new movie", color = Color.White)
            }
        },
    ) { padding ->
        MainContent(modifier = Modifier.padding(padding),
                    addMovie = {coroutineScope.launch{ viewModel.addMovie(it) }},
                    navigateBack = {navController.popBackStack()},
                    validateMovie = {validateMovie -> viewModel.validateMovie(validateMovie)})
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(modifier: Modifier = Modifier,
                addMovie : (Movie) -> Unit,
                navigateBack : (String) -> Unit,
                validateMovie : (Movie) -> List<Boolean>) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            var title by remember {
                mutableStateOf("")
            }

            var titleError by remember {
                mutableStateOf(false)
            }

            var year by remember {
                mutableStateOf("")
            }

            var yearError by remember {
                mutableStateOf(false)
            }

            val genres = Genre.values().toList()

            var genreItems by remember {
                mutableStateOf(
                    genres.map { genre ->
                        ListItemSelectable(
                            title = genre.toString(),
                            isSelected = false
                        )
                    }
                )
            }

            var genreError by remember {
                mutableStateOf(false)
            }

            var director by remember {
                mutableStateOf("")
            }

            var directorError by remember {
                mutableStateOf(false)
            }

            var actors by remember {
                mutableStateOf("")
            }

            var actorsError by remember {
                mutableStateOf(false)
            }

            var plot by remember {
                mutableStateOf("")
            }

            var plotError by remember {
                mutableStateOf(false)
            }

            var rating by remember {
                mutableStateOf("")
            }

            var ratingError by remember {
                mutableStateOf(false)
            }

            var isEnabledSaveButton by remember {
                mutableStateOf(false)
            }

            lateinit var movie : Movie

            OutlinedTextField(
                value = title,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { title = it
                    movie.title = it
                    titleError = validateMovie(movie)[0]
                    isEnabledSaveButton = validateMovie(movie).none { it }},
                label = { Text(text = stringResource(R.string.enter_movie_title)) },
                isError = titleError
            )
            ErrorText(isError = titleError , text = "Title is required")

            OutlinedTextField(
                value = year,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { year = it
                                movie.year = it
                                yearError = validateMovie(movie)[1]
                    isEnabledSaveButton = validateMovie(movie).none { it }},
                label = { Text(stringResource(R.string.enter_movie_year)) },
                isError = yearError
            )
            ErrorText(isError = yearError , text = "Year is required")

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(R.string.select_genres),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6)

            LazyHorizontalGrid(
                modifier = Modifier.height(100.dp),
                rows = GridCells.Fixed(3)){
                items(genreItems) { genreItem ->
                    Chip(
                        modifier = Modifier.padding(2.dp),
                        colors = ChipDefaults.chipColors(
                            backgroundColor = if (genreItem.isSelected)
                                colorResource(id = R.color.purple_200)
                            else
                                colorResource(id = R.color.white)
                        ),
                        onClick = {
                            genreItems = genreItems.map {
                                if (it.title == genreItem.title) {
                                    genreItem.copy(isSelected = !genreItem.isSelected)
                                } else {
                                    it
                                }
                            }
                            movie.genre = genreList(genreItems)
                            genreError = validateMovie(movie)[2]
                            isEnabledSaveButton = validateMovie(movie).none { it }
                        }
                    ) {
                        Text(text = genreItem.title)
                    }
                }
            }
            ErrorText(isError = genreError , text = "Genre is required")

            OutlinedTextField(
                value = director,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { director = it
                                movie.director = it
                                directorError = validateMovie(movie)[3]
                    isEnabledSaveButton = validateMovie(movie).none { it }},
                label = { Text(stringResource(R.string.enter_director)) },
                isError = directorError
            )
            ErrorText(isError = directorError , text = "Director is required")

            OutlinedTextField(
                value = actors,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { actors = it
                                movie.actors = it
                                actorsError = validateMovie(movie)[4]
                    isEnabledSaveButton = validateMovie(movie).none { it }},
                label = { Text(stringResource(R.string.enter_actors)) },
                isError = actorsError
            )
            ErrorText(isError = actorsError , text = "Actors are required")

            OutlinedTextField(
                value = plot,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                onValueChange = { plot = it
                                movie.plot = it
                                plotError = validateMovie(movie)[5]
                    isEnabledSaveButton = validateMovie(movie).none { it }},
                label = { Text(textAlign = TextAlign.Start, text = stringResource(R.string.enter_plot)) },
                isError = plotError
            )
            ErrorText(isError = plotError , text = "Plot is required")

            OutlinedTextField(
                value = rating,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    rating = if(it.startsWith("0")) {
                        ""
                    } else {
                        it
                    }
                    movie.rating = if (rating.toFloatOrNull() != null) rating.toFloat() else 0.0f
                    ratingError = validateMovie(movie)[6]
                    isEnabledSaveButton = validateMovie(movie).none { it }
                },
                label = { Text(stringResource(R.string.enter_rating)) },
                isError = ratingError
            )
            ErrorText(isError = ratingError , text = "Rating is required and must be a number")

            movie = Movie(
                //id = 1,
                title = title,
                year = year,
                genre = genreList(genreItems),
                director = director,
                actors = actors,
                plot = plot,
                images = listOf<String>("default"),
                rating = if (rating.toFloatOrNull() != null) rating.toFloat() else 0.0f,
                isFavorite = false
            )

            Button(
                enabled = isEnabledSaveButton,
                onClick = { addMovie(movie)
                            navigateBack("okay")}) {
                Text(text = stringResource(R.string.add))
            }
        }
    }
}

fun genreList(genreItems : List<ListItemSelectable>): List<Genre>{
    var genreList = mutableListOf<Genre>()

    genreItems.forEach(){
        if (it.isSelected) genreList.add(enumValueOf(it.title))
    }

    return genreList
}

@Composable
fun ErrorText(isError : Boolean, text : String){
    if (isError){
        Text(text = text,
            color = Color.Red,
            fontSize = 10.sp
        )
    }
}

fun checkAddable(titleError: Boolean,
                 yearError: Boolean,
                 genreError: Boolean,
                 directorError: Boolean,
                 actorError: Boolean,
                 plotError: Boolean,
                 ratingError: Boolean): Boolean{
    if (!titleError && !yearError && !genreError && !directorError && !actorError && !plotError && !ratingError){
        return true
    }
    return false
}