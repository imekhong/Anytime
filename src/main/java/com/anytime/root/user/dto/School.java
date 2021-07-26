
package com.anytime.root.user.dto;

import java.util.List;

public class School {

	private DataSearch dataSearch;

	public DataSearch getDataSearch() {
		return dataSearch;
	}

	public void setDataSearch(DataSearch dataSearch) {
		this.dataSearch = dataSearch;
	}

	public School withDataSearch(DataSearch dataSearch) {
		this.dataSearch = dataSearch;
		return this;
	}

	static public class DataSearch {

		private List<Content> content = null;

		public List<Content> getContent() {
			return content;
		}

		public void setContent(List<Content> content) {
			this.content = content;
		}

		public DataSearch withContent(List<Content> content) {
			this.content = content;
			return this;
		}

		static public class Content {

			private String schoolType;
			private String link;
			private String schoolGubun;
			private String adres;
			private String schoolName;
			private String region;
			private String totalCount;
			private String estType;
			private String seq;

			public String getSchoolType() {
				return schoolType;
			}

			public void setSchoolType(String schoolType) {
				this.schoolType = schoolType;
			}

			public Content withSchoolType(String schoolType) {
				this.schoolType = schoolType;
				return this;
			}

			public String getLink() {
				return link;
			}

			public void setLink(String link) {
				this.link = link;
			}

			public Content withLink(String link) {
				this.link = link;
				return this;
			}

			public String getSchoolGubun() {
				return schoolGubun;
			}

			public void setSchoolGubun(String schoolGubun) {
				this.schoolGubun = schoolGubun;
			}

			public Content withSchoolGubun(String schoolGubun) {
				this.schoolGubun = schoolGubun;
				return this;
			}

			public String getAdres() {
				return adres;
			}

			public void setAdres(String adres) {
				this.adres = adres;
			}

			public Content withAdres(String adres) {
				this.adres = adres;
				return this;
			}

			public String getSchoolName() {
				return schoolName;
			}

			public void setSchoolName(String schoolName) {
				this.schoolName = schoolName;
			}

			public Content withSchoolName(String schoolName) {
				this.schoolName = schoolName;
				return this;
			}

			public String getRegion() {
				return region;
			}

			public void setRegion(String region) {
				this.region = region;
			}

			public Content withRegion(String region) {
				this.region = region;
				return this;
			}

			public String getTotalCount() {
				return totalCount;
			}

			public void setTotalCount(String totalCount) {
				this.totalCount = totalCount;
			}

			public Content withTotalCount(String totalCount) {
				this.totalCount = totalCount;
				return this;
			}

			public String getEstType() {
				return estType;
			}

			public void setEstType(String estType) {
				this.estType = estType;
			}

			public Content withEstType(String estType) {
				this.estType = estType;
				return this;
			}

			public String getSeq() {
				return seq;
			}

			public void setSeq(String seq) {
				this.seq = seq;
			}

			public Content withSeq(String seq) {
				this.seq = seq;
				return this;
			}

		}

	}

}
