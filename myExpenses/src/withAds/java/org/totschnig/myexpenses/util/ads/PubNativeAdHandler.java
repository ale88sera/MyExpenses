package org.totschnig.myexpenses.util.ads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.pubnative.mediation.request.PubnativeNetworkRequest;
import net.pubnative.mediation.request.model.PubnativeAdModel;

import org.totschnig.myexpenses.R;

import timber.log.Timber;

class PubNativeAdHandler extends AdHandler {
  private final Context context;
  private View title;
  private View description;
  private View icon;
  private View rating;
  private ViewGroup adViewContainer;
  private View banner;

  public PubNativeAdHandler(ViewGroup adContainer) {
    super(adContainer);
    this.context = adContainer.getContext();
  }

  @Override
  public void init() {
    PubnativeNetworkRequest request = new PubnativeNetworkRequest();
    adViewContainer = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.pubnative, adContainer, false);
    title = adViewContainer.findViewById(R.id.ad_title_text);
    description = adViewContainer.findViewById(R.id.ad_description_text);
    icon = adViewContainer.findViewById(R.id.ad_icon_image);
    banner = adViewContainer.findViewById(R.id.ad_banner_image);
    rating = adViewContainer.findViewById(R.id.ad_rating);
    adContainer.addView(adViewContainer);
    request.start(context, "d7757800d02945a18bbae190a9a7d4d1", "default", new PubnativeNetworkRequest.Listener() {

      @Override
      public void onPubnativeNetworkRequestLoaded(PubnativeNetworkRequest request, PubnativeAdModel ad) {
        ad.withTitle(title)
            .withDescription(description)
            .withIcon(icon)
            .withBanner(banner)
            .withRating(rating)
            .startTracking(context, adViewContainer);
      }

      @Override
      public void onPubnativeNetworkRequestFailed(PubnativeNetworkRequest request, Exception exception) {
        Timber.e(exception);
      }
    });
  }
}
