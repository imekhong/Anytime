package com.anytime.root.user.dto;

public class KakaoProfile {

	private Integer id;
	private String connected_at;
	private Properties properties;
	private Kakao_account kakao_account;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public KakaoProfile withId(Integer id) {
		this.id = id;
		return this;
	}

	public String getConnected_at() {
		return connected_at;
	}

	public void setConnected_at(String connected_at) {
		this.connected_at = connected_at;
	}

	public KakaoProfile withConnected_at(String connected_at) {
		this.connected_at = connected_at;
		return this;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public KakaoProfile withProperties(Properties properties) {
		this.properties = properties;
		return this;
	}

	public Kakao_account getKakao_account() {
		return kakao_account;
	}

	public void setKakao_account(Kakao_account kakao_account) {
		this.kakao_account = kakao_account;
	}

	public KakaoProfile withKakao_account(Kakao_account kakao_account) {
		this.kakao_account = kakao_account;
		return this;
	}

	public class Properties {

		private String nickname;
		private String profile_image;
		private String thumbnail_image;

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public Properties withNickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public String getProfile_image() {
			return profile_image;
		}

		public void setProfile_image(String profile_image) {
			this.profile_image = profile_image;
		}

		public Properties withProfile_image(String profile_image) {
			this.profile_image = profile_image;
			return this;
		}

		public String getThumbnail_image() {
			return thumbnail_image;
		}

		public void setThumbnail_image(String thumbnail_image) {
			this.thumbnail_image = thumbnail_image;
		}

		public Properties withThumbnail_image(String thumbnail_image) {
			this.thumbnail_image = thumbnail_image;
			return this;
		}

	}

	public class Kakao_account {

		private Boolean profile_needs_agreement;
		private Profile profile;
		private Boolean has_email;
		private Boolean email_needs_agreement;
		private Boolean is_email_valid;
		private Boolean is_email_verified;
		private String email;
		private Boolean has_age_range;
		private Boolean age_range_needs_agreement;
		private String age_range;

		public Boolean getProfile_needs_agreement() {
			return profile_needs_agreement;
		}

		public void setProfile_needs_agreement(Boolean profile_needs_agreement) {
			this.profile_needs_agreement = profile_needs_agreement;
		}

		public Kakao_account withProfile_needs_agreement(Boolean profile_needs_agreement) {
			this.profile_needs_agreement = profile_needs_agreement;
			return this;
		}

		public Profile getProfile() {
			return profile;
		}

		public void setProfile(Profile profile) {
			this.profile = profile;
		}

		public Kakao_account withProfile(Profile profile) {
			this.profile = profile;
			return this;
		}

		public Boolean getHas_email() {
			return has_email;
		}

		public void setHas_email(Boolean has_email) {
			this.has_email = has_email;
		}

		public Kakao_account withHas_email(Boolean has_email) {
			this.has_email = has_email;
			return this;
		}

		public Boolean getEmail_needs_agreement() {
			return email_needs_agreement;
		}

		public void setEmail_needs_agreement(Boolean email_needs_agreement) {
			this.email_needs_agreement = email_needs_agreement;
		}

		public Kakao_account withEmail_needs_agreement(Boolean email_needs_agreement) {
			this.email_needs_agreement = email_needs_agreement;
			return this;
		}

		public Boolean getIs_email_valid() {
			return is_email_valid;
		}

		public void setIs_email_valid(Boolean is_email_valid) {
			this.is_email_valid = is_email_valid;
		}

		public Kakao_account withIs_email_valid(Boolean is_email_valid) {
			this.is_email_valid = is_email_valid;
			return this;
		}

		public Boolean getIs_email_verified() {
			return is_email_verified;
		}

		public void setIs_email_verified(Boolean is_email_verified) {
			this.is_email_verified = is_email_verified;
		}

		public Kakao_account withIs_email_verified(Boolean is_email_verified) {
			this.is_email_verified = is_email_verified;
			return this;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Kakao_account withEmail(String email) {
			this.email = email;
			return this;
		}

		public Boolean getHas_age_range() {
			return has_age_range;
		}

		public void setHas_age_range(Boolean has_age_range) {
			this.has_age_range = has_age_range;
		}

		public Kakao_account withHas_age_range(Boolean has_age_range) {
			this.has_age_range = has_age_range;
			return this;
		}

		public Boolean getAge_range_needs_agreement() {
			return age_range_needs_agreement;
		}

		public void setAge_range_needs_agreement(Boolean age_range_needs_agreement) {
			this.age_range_needs_agreement = age_range_needs_agreement;
		}

		public Kakao_account withAge_range_needs_agreement(Boolean age_range_needs_agreement) {
			this.age_range_needs_agreement = age_range_needs_agreement;
			return this;
		}

		public String getAge_range() {
			return age_range;
		}

		public void setAge_range(String age_range) {
			this.age_range = age_range;
		}

		public Kakao_account withAge_range(String age_range) {
			this.age_range = age_range;
			return this;
		}

		public class Profile {
			private String nickname;
			private String thumbnail_image_url;
			private String profile_image_url;
			private Boolean is_default_image;

			public String getNickname() {
				return nickname;
			}

			public void setNickname(String nickname) {
				this.nickname = nickname;
			}

			public Profile withNickname(String nickname) {
				this.nickname = nickname;
				return this;
			}

			public String getThumbnail_image_url() {
				return thumbnail_image_url;
			}

			public void setThumbnail_image_url(String thumbnail_image_url) {
				this.thumbnail_image_url = thumbnail_image_url;
			}

			public Profile withThumbnail_image_url(String thumbnail_image_url) {
				this.thumbnail_image_url = thumbnail_image_url;
				return this;
			}

			public String getProfile_image_url() {
				return profile_image_url;
			}

			public void setProfile_image_url(String profile_image_url) {
				this.profile_image_url = profile_image_url;
			}

			public Profile withProfile_image_url(String profile_image_url) {
				this.profile_image_url = profile_image_url;
				return this;
			}

			public Boolean getIs_default_image() {
				return is_default_image;
			}

			public void setIs_default_image(Boolean is_default_image) {
				this.is_default_image = is_default_image;
			}

			public Profile withIs_default_image(Boolean is_default_image) {
				this.is_default_image = is_default_image;
				return this;
			}

		}

	}

}
