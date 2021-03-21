package com.MyAPI.service;

import java.util.Comparator;

import com.MyAPI.model.Posts;

public class FieldComparator implements Comparator<Posts> {

	private String _sort;
	private String _order;

	public void set_sort(String _sort, String _order) {
		this._sort = _sort;
		this._order = _order;
	}

	

	@Override
	public int compare(Posts o1, Posts o2) {

		if (this._sort.equals( "views")) {
			if (this._order.equals("asc"))
				return (o1.getViews().get().compareTo(o2.getViews().get()));
			else if (this._order.equals("dsc"))
				return (o2.getViews().get().compareTo(o1.getViews().get()));

		} else if (this._sort.equals( "title")) {

			if (this._order.equals("asc"))
				return (o1.getTitle().get().compareTo(o2.getTitle().get()));
			else if (this._order.equals("dsc"))
				return (o2.getTitle().get().compareTo(o1.getTitle().get()));

		} else if (this._sort.equals("reviews")) {

			if (this._order.equals("asc"))
				return (o1.getReviews().get().compareTo(o2.getReviews().get()));
			else if (this._order.equals("dsc"))
				return (o2.getReviews().get().compareTo(o1.getReviews().get()));

		} else if (this._sort.equals("author")) {

			if (this._order.equals("asc"))
				return (o1.getAuthor().get().compareTo(o2.getAuthor().get()));
			else if (this._order.equals("dsc"))
				return (o2.getAuthor().get().compareTo(o1.getAuthor().get()));

		}
		return 0;
	}

}
