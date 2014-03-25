///	***	User :: Controller :: Song	***	***	***	***	***	***	***	***	***	***	///

	/**	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	*
	 *																	*
	 * @copyright 2014 (c), by Coffeine
	 *
	 * @author Vitaliy Tsutsman <vitaliyacm@gmail.com>
	 *
	 * @date 2014-03-25 15:26:32 :: ....-..-.. ..:..:..
	 *
	 * @address /Ukraine/Ivano-Frankivsk/Tychyny/7a
	 *																	*
	*///***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	*

///	***	Code	***	***	***	***	***	***	***	***	***	***	***	***	***	***	***	///
package com.coffeine.virtuoso.module.user.controller;

import com.coffeine.virtuoso.module.user.model.entity.Song;
import com.coffeine.virtuoso.module.user.model.repository.SongRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * SongController
 */
@Controller
@RequestMapping( value = "/user/song" )
public class SongController {

	//TODO: tmp
//	private SongRepository songRespository;
//
//	@Autowired
//	public SongController( SongRepository songRepository ) {
//		this.songRespository = songRepository;
//	}

	//- SECTION :: ACTIONS -//
	@RequestMapping( value = "/list", method = RequestMethod.GET )
	@ResponseStatus( value = HttpStatus.OK )
	@ResponseBody
	public List< Song > listAction( Model model ) {
		//- Get list of song from persistence layout -//
		List < Song > songList = new ArrayList < Song >();//songRespository.findAll();
			songList.add(
				new Song(
					1L,
					"Coffeine"
				)
			);

		return songList;
	}

	@RequestMapping( method = RequestMethod.GET )
	public String indexAction( Model model ) {
		return "test";
	}

    //Use onSubmit instead of doSubmitAction
	//when you need access to the Request, Response, or BindException objects
    /*
	 @Override
	 protected ModelAndView onSubmit(
	 HttpServletRequest request,
	 HttpServletResponse response,
	 Object command,
	 BindException errors) throws Exception {
	 ModelAndView mv = new ModelAndView(getSuccessView());
	 //Do something...
	 return mv;
	 }
	 */
}
