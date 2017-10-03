package qcryptic.sphin.vo;

/**
 * Created by Kyle on 10/1/2017.
 */
public class TvSearchResultVo {

    private String name;
    private Long id;

    public TvSearchResultVo(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
