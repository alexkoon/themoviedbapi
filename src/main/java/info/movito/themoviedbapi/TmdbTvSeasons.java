package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.tv.TvSeason;
import info.movito.themoviedbapi.tools.ApiUrl;
import org.apache.commons.lang3.StringUtils;


public class TmdbTvSeasons extends AbstractTmdbApi {

    public static final String TMDB_METHOD_TV_SEASON = "season";


    public static enum SeasonMethod {
        // base method shared by all tv apis
        credits, external_ids, images, videos
        // specific method for episodes
        // ...tbd
    }


    TmdbTvSeasons(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    public TvSeason getSeason(int seriesId, int seasonNumber, String language, SeasonMethod... appendToResponse) {
        ApiUrl apiUrl = new ApiUrl(TmdbTV.TMDB_METHOD_TV, seriesId, TMDB_METHOD_TV_SEASON, seasonNumber);

        if (StringUtils.isNotBlank(language)) {
            apiUrl.addParam(PARAM_LANGUAGE, language);
        }

        apiUrl.appendToResponse(Utils.asStringArray(appendToResponse));

        return mapJsonResult(apiUrl, TvSeason.class);
    }
}
