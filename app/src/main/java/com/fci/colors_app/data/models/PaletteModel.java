package com.fci.colors_app.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PaletteModel implements Serializable {

    @SerializedName("schemes")
    private List<SchemesBean> schemes;
    @SerializedName("success")
    private boolean success;
    @SerializedName("messages")
    private List<String> messages;

    public List<SchemesBean> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<SchemesBean> schemes) {
        this.schemes = schemes;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<?> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public static class SchemesBean implements Serializable {
        @SerializedName("colors")
        private List<String> colors;
        @SerializedName("id")
        private String id;
        @SerializedName("tags")
        private List<TagsBean> tags;

        public List<String> getColors() {
            return colors;
        }

        public void setColors(List<String> colors) {
            this.colors = colors;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean implements Serializable {
            @SerializedName("id")
            private String id;
            @SerializedName("name")
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
